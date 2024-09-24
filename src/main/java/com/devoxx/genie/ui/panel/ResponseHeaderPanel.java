package com.devoxx.genie.ui.panel;

import com.devoxx.genie.IntellijProjectHandler;
import com.devoxx.genie.model.LanguageModel;
import com.devoxx.genie.model.request.ChatMessageContext;
import com.devoxx.genie.ui.component.JHoverButton;
import com.devoxx.genie.ui.util.NotificationUtil;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.time.format.DateTimeFormatter;

import static com.devoxx.genie.chatmodel.ChatModelFactory.TEST_MODEL;
import static com.devoxx.genie.ui.util.DevoxxGenieIconsUtil.CopyIcon;

public class ResponseHeaderPanel extends JBPanel<ResponseHeaderPanel> {

    /**
     * The response header panel.
     *
     * @param chatMessageContext the chat message context
     */
    public ResponseHeaderPanel(@NotNull ChatMessageContext chatMessageContext) {
        super(new BorderLayout());

        andTransparent()
            .withMaximumHeight(30)
            .withPreferredHeight(30);

        add(getCreatedOnLabel(chatMessageContext), BorderLayout.WEST);
        add(createCopyButton(chatMessageContext), BorderLayout.EAST);
    }

    /**
     * Get the created on label.
     *
     * @param chatMessageContext the chat message context
     * @return the created on label
     */
    private static @NotNull JBLabel getCreatedOnLabel(@NotNull ChatMessageContext chatMessageContext) {
        LanguageModel languageModel = chatMessageContext.getLanguageModel();
        String modelInfo = languageModel.getProvider().getName() +
            (languageModel.getModelName() != null && !languageModel.getModelName().equalsIgnoreCase(TEST_MODEL) ?
                " (" + languageModel.getModelName() + ")" : "");

        String label = chatMessageContext.getCreatedOn().format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm")) + " : " + modelInfo;
        JBLabel createdOnLabel = new JBLabel(label, SwingConstants.LEFT);
        createdOnLabel.setFont(createdOnLabel.getFont().deriveFont(12f));
        createdOnLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 10));
        return createdOnLabel;
    }

    /**
     * Create the Copy button to copy prompt response.
     *
     * @param chatMessageContext the chat message context
     * @return the Delete button
     */
    private @NotNull JButton createCopyButton(ChatMessageContext chatMessageContext) {
        JButton deleteButton = new JHoverButton(CopyIcon, true);
        deleteButton.setToolTipText("Copy prompt response");
        deleteButton.addActionListener(e -> copyPrompt(chatMessageContext));
        return deleteButton;
    }

    /**
     * Copy the prompt response to the system clipboard.
     *
     * @param chatMessageContext the chat message context
     */
    private void copyPrompt(@NotNull ChatMessageContext chatMessageContext) {
        String response = chatMessageContext.getAiMessage().text();
        Transferable transferable = new StringSelection(response);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(transferable, null);
        NotificationUtil.sendNotification(
            IntellijProjectHandler.intellijProjectFrom(chatMessageContext.getProject()),
            "The prompt response has been copied to the clipboard"
        );
    }
}
