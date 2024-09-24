package com.devoxx.genie.service;

import java.lang.reflect.Constructor;
import java.util.ServiceLoader;

public interface DevoxxGenieServiceProvider {

    static DevoxxGenieServiceProvider getServiceProvider() {
        try {
            // Workaround to load the SPI implementation in the IntelliJ plugin
            Class<?> cl = Class.forName("com.devoxx.genie.service.DevoxxGenieIntelliJServiceProvider");
            Constructor<?> cons = cl.getConstructor();
            return (DevoxxGenieServiceProvider) cons.newInstance();
        } catch (Exception e) {
            // Ignore
        }

        // This is the original code of the SPI implementation using ServiceLoader. Not
        // working in the plugin for unknown reasons.
        ServiceLoader<DevoxxGenieServiceProvider> serviceLoader = ServiceLoader.load(DevoxxGenieServiceProvider.class);
        return serviceLoader.findFirst().orElseThrow();
    }

    DevoxxGenieSettingsService getDevoxxGenieSettingsService();

    NotificationService getNotificationService();

    ProjectManager getProjectManager();

    Logger getLogger(Class<?> clazz);

    MessageCreationService getMessageCreationService();

    ChatMemoryService getChatMemoryService();
}
