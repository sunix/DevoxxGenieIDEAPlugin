package com.devoxx.genie.service;

import com.devoxx.genie.ProjectHandler;

public interface ProjectManager {

    static ProjectManager getInstance() {
        return DevoxxGenieServiceProvider.getServiceProvider()
                .getProjectManager();
    }

    ProjectHandler getDefaultProject();

}
