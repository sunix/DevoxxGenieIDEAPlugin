package com.devoxx.genie.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.devoxx.genie.FileAdapter;

@Setter
@Getter
public class EditorInfo {

    private String language;
    private String selectedText;
    private List<FileAdapter> selectedFiles;

    public EditorInfo() {
    }

    public EditorInfo(List<FileAdapter> selectedFiles) {
        this.selectedFiles = selectedFiles;
    }

    public void setSelectedFiles(List<FileAdapter> selectedFiles) {
        this.selectedFiles = new ArrayList<>(selectedFiles);
    }
}
