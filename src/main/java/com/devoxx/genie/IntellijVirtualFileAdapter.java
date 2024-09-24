package com.devoxx.genie;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import com.devoxx.genie.service.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;

import static com.devoxx.genie.action.AddSnippetAction.SELECTED_TEXT_KEY;

public class IntellijVirtualFileAdapter implements FileAdapter {
    private final VirtualFile virtualFile;

    public IntellijVirtualFileAdapter(VirtualFile virtualFile) {
        this.virtualFile = virtualFile;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getAdaptedInstance(Class<T> adapteeClassName) {
        if (adapteeClassName.isInstance(virtualFile)) {
            return (T) virtualFile;
        }
        throw new IllegalArgumentException("Unsupported adaptee class: " + adapteeClassName.getName());
    }

    public static List<FileAdapter> fileHandlerListFrom(List<VirtualFile> virtualFiles) {
        return virtualFiles.stream()
                .map(IntellijVirtualFileAdapter::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getContent() {
        try {
            return new String(virtualFile.contentsToByteArray(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            // think about how to get Project:
            // NotificationService.getInstance().sendNotification()
            Logger.getInstance(getClass()).error("Error reading file: " + e.getMessage(), e);
        }
        return "";
    }

    @Override
    public String getName() {
        return virtualFile.getName();
    }

    @Override
    public String getFileType() {
        return virtualFile.getFileType().getName();
    }

    @Override
    public String getSelectedText() {
        return virtualFile.getUserData(SELECTED_TEXT_KEY);
    }

    @Override
    public String getDocumentContent() {
        FileDocumentManager fileDocumentManager = FileDocumentManager.getInstance();
        Document document = fileDocumentManager.getDocument(virtualFile);
        if (document != null) {

            return document.getText();
        } else {
            // think about how to get Project:
            // NotificationService.getInstance().sendNotification()
            Logger.getInstance(getClass()).error("Error reading file: " + getName());
        }
        return "";
    }
}