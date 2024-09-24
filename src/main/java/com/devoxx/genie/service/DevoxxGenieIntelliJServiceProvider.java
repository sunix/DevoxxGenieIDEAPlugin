package com.devoxx.genie.service;

import com.devoxx.genie.ui.settings.DevoxxGenieStateService;
import com.intellij.openapi.application.ApplicationManager;

public class DevoxxGenieIntelliJServiceProvider implements DevoxxGenieServiceProvider {

    @Override
    public DevoxxGenieSettingsService getDevoxxGenieSettingsService() {
        return ApplicationManager.getApplication().getService(DevoxxGenieStateService.class);
    }

    @Override
    public NotificationService getNotificationService() {
        // get instance of IntelliJNotificationService through ApplicationManager
        return ApplicationManager.getApplication().getService(IntelliJNotificationService.class);
    }

    @Override
    public ProjectManager getProjectManager() {
        return ApplicationManager.getApplication().getService(IntellijProjectManager.class);
    }

    @Override
    public Logger getLogger(Class<?> clazz) {
        return new IntellijLogger(clazz);
    }

    @Override
    public IntellijMessageCreationService getMessageCreationService() {
        return ApplicationManager.getApplication().getService(IntellijMessageCreationService.class);
    }

    @Override
    public ChatMemoryService getChatMemoryService() {
        return IntellijChatMemoryService.getInstance();
    }
}
