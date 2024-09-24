package com.devoxx.genie.service;

import com.devoxx.genie.IntellijProjectHandler;
import com.devoxx.genie.IntellijVirtualFileAdapter;
import com.devoxx.genie.ProjectHandler;
import com.devoxx.genie.model.CustomPrompt;
import com.devoxx.genie.model.request.ChatMessageContext;
import com.devoxx.genie.model.request.EditorInfo;
import com.devoxx.genie.ui.panel.PromptOutputPanel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;

import static com.devoxx.genie.ui.util.DevoxxGenieIconsUtil.AddFileIcon;

import java.util.Arrays;
import java.util.Optional;

public class ChatPromptExecutor {

    private final StreamingPromptExecutor streamingPromptExecutor;
    private final NonStreamingPromptExecutor nonStreamingPromptExecutor;
    private volatile boolean isRunning = false;

    public ChatPromptExecutor() {
        this.streamingPromptExecutor = new StreamingPromptExecutor();
        this.nonStreamingPromptExecutor = new NonStreamingPromptExecutor();
    }

    /**
     * Execute the prompt.
     * @param chatMessageContext the chat message context
     * @param promptOutputPanel  the prompt output panel
     * @param enableButtons      the Enable buttons
     */
    public void executePrompt(@NotNull ChatMessageContext chatMessageContext,
                              PromptOutputPanel promptOutputPanel,
                              Runnable enableButtons) {

        isRunning = true;
        ProjectHandler ph = chatMessageContext.getProject();
        Project project = IntellijProjectHandler.intellijProjectFrom(ph);
        new Task.Backgroundable(project, "Working...", true) {
            @Override
            public void run(@NotNull ProgressIndicator progressIndicator) {
                if (chatMessageContext.isWebSearchRequested()) {
                    new WebSearchExecutor().execute(chatMessageContext, promptOutputPanel, () -> {
                        isRunning = false;
                        enableButtons.run();
                    });
                } else if (DevoxxGenieSettingsService.getInstance().getStreamMode()) {
                    streamingPromptExecutor.execute(chatMessageContext, promptOutputPanel, () -> {
                        isRunning = false;
                        enableButtons.run();
                    });
                } else {
                    nonStreamingPromptExecutor.execute(chatMessageContext, promptOutputPanel, () -> {
                        isRunning = false;
                        enableButtons.run();
                    });
                }
            }
        }.queue();
    }

    /**
     * Process possible command prompt.
     * @param chatMessageContext the chat message context
     * @param promptOutputPanel  the prompt output panel
     */
    public Optional<String> updatePromptWithCommandIfPresent(@NotNull ChatMessageContext chatMessageContext,
                                                             PromptOutputPanel promptOutputPanel) {
        Optional<String> commandFromPrompt = getCommandFromPrompt(chatMessageContext.getUserPrompt().trim(), promptOutputPanel);
        chatMessageContext.setUserPrompt(commandFromPrompt.orElse(chatMessageContext.getUserPrompt()));

        // Ensure that EditorInfo is set in the ChatMessageContext
        if (chatMessageContext.getEditorInfo() == null) {
            chatMessageContext.setEditorInfo(getEditorInfo(IntellijProjectHandler.intellijProjectFrom(chatMessageContext.getProject())));
        }

        return commandFromPrompt;
    }

    private @NotNull EditorInfo getEditorInfo(Project project) {
        EditorInfo editorInfo = new EditorInfo();
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        Editor editor = fileEditorManager.getSelectedTextEditor();

        if (editor != null) {
            String selectedText = editor.getSelectionModel().getSelectedText();
            if (selectedText != null && !selectedText.isEmpty()) {
                editorInfo.setSelectedText(selectedText);
            } else {
                VirtualFile[] openFiles = fileEditorManager.getOpenFiles();
                if (openFiles.length > 0) {
                    editorInfo.setSelectedFiles(IntellijVirtualFileAdapter.fileHandlerListFrom(Arrays.asList(openFiles)));
                }
            }

            PsiFile psiFile = PsiManager.getInstance(project).findFile(fileEditorManager.getSelectedFiles()[0]);
            if (psiFile != null) {
                editorInfo.setLanguage(psiFile.getLanguage().getDisplayName());
            }
        }

        return editorInfo;
    }

    /**
     * Stop streaming or the non-streaming prompt execution
     */
    public void stopPromptExecution() {
        if (isRunning) {
            isRunning = false;
            streamingPromptExecutor.stopStreaming();
            nonStreamingPromptExecutor.stopExecution();
        }
    }

    /**
     * Get the command from the prompt.
     *
     * @param prompt            the prompt
     * @param promptOutputPanel the prompt output panel
     * @return the command
     */
    private Optional<String> getCommandFromPrompt(@NotNull String prompt,
                                                  PromptOutputPanel promptOutputPanel) {
        if (prompt.startsWith("/")) {
            DevoxxGenieSettingsService settings = DevoxxGenieSettingsService.getInstance();

            // Check for custom prompts
            for (CustomPrompt customPrompt : settings.getCustomPrompts()) {
                if (prompt.equalsIgnoreCase("/" + customPrompt.getName())) {
                    prompt = customPrompt.getPrompt();
                    return Optional.of(prompt);
                }
            }
            promptOutputPanel.showHelpText();
            return Optional.empty();
        }
        return Optional.of(prompt);
    }
}
