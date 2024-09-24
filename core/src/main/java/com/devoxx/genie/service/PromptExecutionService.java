package com.devoxx.genie.service;

import com.devoxx.genie.model.Constant;
import com.devoxx.genie.model.request.ChatMessageContext;
import com.devoxx.genie.service.exception.ProviderUnavailableException;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public enum PromptExecutionService {

    INSTANCE;

    private static final Logger LOG = Logger.getInstance(PromptExecutionService.class);

    public static PromptExecutionService getInstance() {
        return INSTANCE;
    }

    private final ExecutorService queryExecutor = Executors.newSingleThreadExecutor();
    private CompletableFuture<Optional<AiMessage>> queryFuture = null;

    @Getter
    private boolean running = false;

    private final ReentrantLock queryLock = new ReentrantLock();

    /**
     * Execute the query with the given language text pair and chat language model.
     *
     * @param chatMessageContext the chat message context
     * @return the response
     */
    public @NotNull CompletableFuture<Optional<AiMessage>> executeQuery(@NotNull ChatMessageContext chatMessageContext) {
        LOG.info("Execute query : " + chatMessageContext);

        queryLock.lock();
        try {
            if (isCanceled()) return queryFuture;

            MessageCreationService messageCreationService = MessageCreationService.getInstance();

            if (ChatMemoryService.getInstance().isEmpty()) {
                LOG.info("ChatMemoryService is empty, adding a new SystemMessage");
                ChatMemoryService.getInstance().add(
                    new SystemMessage(DevoxxGenieSettingsService.getInstance().getSystemPrompt() + Constant.MARKDOWN)
                );
            }

            UserMessage userMessage = messageCreationService.createUserMessage(chatMessageContext);
            LOG.info("Created UserMessage: " + userMessage);

            ChatMemoryService.getInstance().add(userMessage);

            queryFuture = CompletableFuture
                .supplyAsync(() -> processChatMessage(chatMessageContext), queryExecutor)
                .orTimeout(
                    chatMessageContext.getTimeout() == null ? 60 : chatMessageContext.getTimeout() ,
                    TimeUnit.SECONDS)
                .exceptionally(throwable -> {
                    LOG.error("Error occurred while processing chat message", throwable);
                    //ErrorHandler.handleError(chatMessageContext.getProject(), throwable);
                    return Optional.empty();
                });
        } finally {
            queryLock.unlock();
        }
        return queryFuture;
    }

    /**
     * If the future task is not null this means we need to cancel it
     *
     * @return true if the task is canceled
     */
    private boolean isCanceled() {
        if (queryFuture != null && !queryFuture.isDone()) {
            queryFuture.cancel(true);
            running = false;
            return true;
        }
        running = true;
        return false;
    }

    /**
     * Process the chat message.
     * @param chatMessageContext the chat message context
     * @return the AI message
     */
    private @NotNull Optional<AiMessage> processChatMessage(ChatMessageContext chatMessageContext) {
        try {
            ChatLanguageModel chatLanguageModel = chatMessageContext.getChatLanguageModel();
            Response<AiMessage> response = chatLanguageModel.generate(ChatMemoryService.getInstance().messages());
            ChatMemoryService.getInstance().add(response.content());
            return Optional.of(response.content());
        } catch (Exception e) {
            ChatMemoryService.getInstance().removeLast();
            throw new ProviderUnavailableException(e.getMessage());
        }
    }
}
