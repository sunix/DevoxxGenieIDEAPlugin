package com.devoxx.genie.service;

import com.devoxx.genie.ProjectHandler;

public interface NotificationService {

    static NotificationService getInstance() {
        return DevoxxGenieServiceProvider.getServiceProvider()
                .getNotificationService();
    }

    void sendNotification(ProjectHandler project, String content);

}
