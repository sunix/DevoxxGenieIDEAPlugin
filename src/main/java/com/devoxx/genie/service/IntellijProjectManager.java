package com.devoxx.genie.service;

import org.jetbrains.annotations.NotNull;

import com.devoxx.genie.IntellijProjectHandler;
import com.devoxx.genie.ProjectHandler;
import com.intellij.openapi.project.Project;

public class IntellijProjectManager implements ProjectManager {

    @Override
    public ProjectHandler getDefaultProject() {
        @NotNull
        Project project = com.intellij.openapi.project.ProjectManager.getInstance().getDefaultProject();
        return new IntellijProjectHandler(project);
    }

}
