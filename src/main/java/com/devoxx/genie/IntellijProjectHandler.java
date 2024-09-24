package com.devoxx.genie;

import java.util.List;
import java.util.stream.Collectors;

import com.intellij.openapi.project.Project;

public class IntellijProjectHandler implements ProjectHandler {
    private final Project intellijProject;

    public IntellijProjectHandler(Project intellijProject) {
        this.intellijProject = intellijProject;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getAdaptedInstance(Class<T> adapteeClassName) {
        if (adapteeClassName.isInstance(intellijProject)) {
            return (T) intellijProject;
        }
        throw new IllegalArgumentException("Unsupported adaptee class: " + adapteeClassName.getName());
    }


    public static Project intellijProjectFrom(ProjectHandler projectHandler) {
        return projectHandler.getAdaptedInstance(Project.class);
    }

    public static List<ProjectHandler> projectHandlerListFrom(List<Project> projects) {
        return projects.stream()
                .map(IntellijProjectHandler::new)
                .collect(Collectors.toList());
    }

    public static List<Project> intellijProjectListFrom(List<ProjectHandler> projectHandlers) {
        return projectHandlers.stream()
                .map(IntellijProjectHandler::intellijProjectFrom)
                .collect(Collectors.toList());
    }


}