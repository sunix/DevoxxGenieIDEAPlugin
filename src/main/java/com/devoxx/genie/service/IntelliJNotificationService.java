package com.devoxx.genie.service;

import com.devoxx.genie.ProjectHandler;

public class IntelliJNotificationService implements NotificationService {

    @Override
    public void sendNotification(ProjectHandler project, String content) {
        com.devoxx.genie.ui.util.NotificationUtil.sendNotification(project.getAdaptedInstance(com.intellij.openapi.project.Project.class), content);
    }

    
}
