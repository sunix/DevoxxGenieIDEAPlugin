package com.devoxx.genie.service;

import com.devoxx.genie.model.request.ChatMessageContext;
import com.devoxx.genie.ui.panel.PromptOutputPanel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import org.jetbrains.annotations.NotNull;

public class StreamingPromptExecutor {

    private final IntellijChatMemoryService chatMemoryService;
    private final IntellijMessageCreationService messageCreationService;
    private StreamingResponseHandler currentStreamingHandler;

    public StreamingPromptExecutor() {
        this.chatMemoryService = IntellijChatMemoryService.getInstance();
        this.messageCreationService = IntellijMessageCreationService.getInstance();
    }

    /**
     * Execute the streaming prompt.
     * @param chatMessageContext the chat message context
     * @param promptOutputPanel the prompt output panel
     * @param enableButtons the enable buttons
     */
    public void execute(@NotNull ChatMessageContext chatMessageContext,
                        PromptOutputPanel promptOutputPanel,
                        Runnable enableButtons) {
        StreamingChatLanguageModel streamingChatLanguageModel = chatMessageContext.getStreamingChatLanguageModel();
        if (streamingChatLanguageModel == null) {
            NotificationService.getInstance().sendNotification(chatMessageContext.getProject(),
                "Streaming model not available, please select another provider or turn off streaming mode.");
            enableButtons.run();
            return;
        }

        prepareMemory(chatMessageContext);
        promptOutputPanel.addUserPrompt(chatMessageContext);

        currentStreamingHandler = new StreamingResponseHandler(chatMessageContext, promptOutputPanel, enableButtons);
        streamingChatLanguageModel.generate(chatMemoryService.messages(), currentStreamingHandler);
    }

    /**
     * Prepare memory.
     * @param chatMessageContext the chat message context
     */
    private void prepareMemory(ChatMessageContext chatMessageContext) {
        if (chatMemoryService.isEmpty()) {
            chatMemoryService.add(new SystemMessage(DevoxxGenieSettingsService.getInstance().getSystemPrompt()));
        }

        UserMessage userMessage = messageCreationService.createUserMessage(chatMessageContext);
        chatMemoryService.add(userMessage);
    }

    /**
     * Stop streaming.
     */
    public void stopStreaming() {
        if (currentStreamingHandler != null) {
            currentStreamingHandler.stop();
            currentStreamingHandler = null;
        }
    }
}
