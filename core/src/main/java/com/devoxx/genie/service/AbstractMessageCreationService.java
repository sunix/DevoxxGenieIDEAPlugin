package com.devoxx.genie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.NotNull;

import com.devoxx.genie.FileAdapter;
import com.devoxx.genie.ProjectHandler;
import com.devoxx.genie.model.request.ChatMessageContext;
import com.devoxx.genie.model.request.EditorInfo;

import dev.langchain4j.data.message.UserMessage;

public abstract class AbstractMessageCreationService implements MessageCreationService {

    public static final String CONTEXT_PROMPT = "Context: \n";

    @NotNull
    public UserMessage createUserMessage(@NotNull ChatMessageContext chatMessageContext) {
        UserMessage userMessage;
        String context = chatMessageContext.getContext();

        if (context != null && !context.isEmpty() && !chatMessageContext.isFullProjectContextAdded()) {
            // This is likely the full project context scenario
            userMessage = constructUserMessageWithFullContext(chatMessageContext, context);
            chatMessageContext.setFullProjectContextAdded(true);
        } else {
            // Here we include the editor content instead
            userMessage = constructUserMessageWithEditorContent(chatMessageContext);
        }

        return userMessage;
    }

    private @NotNull UserMessage constructUserMessageWithEditorContent(@NotNull ChatMessageContext chatMessageContext) {
        StringBuilder stringBuilder = new StringBuilder();

        // The user prompt is always added
        appendIfNotEmpty(stringBuilder, chatMessageContext.getUserPrompt());


        // Add the editor content or selected text
        String editorContent = getEditorContentOrSelectedText(chatMessageContext);

        if (!editorContent.isEmpty()) {
            // Add the context prompt if it is not empty
            appendIfNotEmpty(stringBuilder, CONTEXT_PROMPT);
            appendIfNotEmpty(stringBuilder, editorContent);
        }

        if (DevoxxGenieSettingsService.getInstance().getAstMode()) {
            addASTContext(chatMessageContext, stringBuilder);
        }

        UserMessage userMessage = new UserMessage(stringBuilder.toString());
        chatMessageContext.setUserMessage(userMessage);
        return userMessage;
    }

    private @NotNull String getEditorContentOrSelectedText(@NotNull ChatMessageContext chatMessageContext) {
        EditorInfo editorInfo = chatMessageContext.getEditorInfo();
        if (editorInfo == null) {
            return "";
        }

        StringBuilder contentBuilder = new StringBuilder();

        // Add selected text if present
        if (editorInfo.getSelectedText() != null && !editorInfo.getSelectedText().isEmpty()) {
            contentBuilder.append("Selected Text:\n")
                .append(editorInfo.getSelectedText())
                .append("\n\n");
        }

        // Add content of selected files
        List<FileAdapter> selectedFiles = editorInfo.getSelectedFiles();

        if (!selectedFiles.isEmpty()) {
            contentBuilder.append("File Contents:\n");
            for (FileAdapter file : selectedFiles) {
                contentBuilder.append("File: ").append(file.getName()).append("\n")
                    .append(file.getContent())
                    .append("\n\n");
            }
        }

        return contentBuilder.toString().trim();
    }


    /**
     * Construct user message with full context.
     * @param chatMessageContext the chat message context
     * @param context the context
     * @return the user message
     */
    private @NotNull UserMessage constructUserMessageWithFullContext(@NotNull ChatMessageContext chatMessageContext,
                                                                     String context) {
        StringBuilder stringBuilder = new StringBuilder();

        // Check if this is the first message in the conversation
        if (ChatMemoryService.getInstance().messages().size() == 1) {
            stringBuilder.append(context);
            stringBuilder.append("\n\n");
            stringBuilder.append("=========================================\n\n");
        }

        stringBuilder.append("User Question: ");
        stringBuilder.append(chatMessageContext.getUserPrompt());

        UserMessage userMessage = new UserMessage("user_message", stringBuilder.toString());
        chatMessageContext.setUserMessage(userMessage);
        return userMessage;
    }

    /**
     * Create user prompt with context.
     * @param project    the project
     * @param userPrompt the user prompt
     * @param files      the files
     * @return the user prompt with context
     */
    public @NotNull CompletableFuture<String> createUserPromptWithContextAsync(ProjectHandler project,
                                                                               String userPrompt,
                                                                               @NotNull List<FileAdapter> files) {
        return CompletableFuture.supplyAsync(() -> {
            StringBuilder userPromptContext = new StringBuilder();

            for (FileAdapter file : files) {
                runReadAction(() -> { // ca fait quoi ? ça permet de lire des fichiers en mode read-only
                    if (file.getFileType().equals("UNKNOWN")) {
                        userPromptContext.append("Filename: ").append(file.getName()).append("\n");
                        userPromptContext.append("Code Snippet: ").append(file.getSelectedText()).append("\n");
                    } else {
                        String docContent = file.getDocumentContent();
                        if (docContent != null) {
                            userPromptContext.append("Filename: ").append(file.getName()).append("\n");
                            String content = file.getDocumentContent();
                            userPromptContext.append(content).append("\n");
                        } else {
                            NotificationService.getInstance().sendNotification(project, "Error reading file: " + file.getName());
                        }
                    }
                });
            }

            userPromptContext.append(userPrompt);
            return userPromptContext.toString();
        });
    }

    protected abstract void runReadAction(Runnable runnable);

    /**
     * Add AST prompt context (selected code snippet) to the chat message.
     * @param chatMessageContext the chat message context
     * @param sb                 the string builder
     */
    abstract void addASTContext(@NotNull ChatMessageContext chatMessageContext,
                                      @NotNull StringBuilder sb);

    /**
     * Append the text to the string builder if it is not empty.
     * @param sb   the string builder
     * @param text the text
     */
    private void appendIfNotEmpty(StringBuilder sb, String text) {
        if (text != null && !text.isEmpty()) {
            sb.append(text).append("\n");
        }
    }

}
