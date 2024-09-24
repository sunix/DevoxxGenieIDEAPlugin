package com.devoxx.genie;

public interface FileAdapter {
    <T> T getAdaptedInstance(Class<T> adapteeClassName);

    // get file content as string
    String getContent();

    String getName();

    String getFileType();

    String getSelectedText();

    String getDocumentContent();
}
