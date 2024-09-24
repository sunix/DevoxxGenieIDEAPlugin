package com.devoxx.genie.service;

public interface Logger {

    static Logger getInstance(Class<?> clazz) {
        return DevoxxGenieServiceProvider.getServiceProvider().getLogger(clazz);
    }
    
    void error(String message);
    void warn(String message);
    void info(String message);
    void debug(String message);
    void trace(String message);
    void error(String message, Throwable t);
    void warn(String message, Throwable t);
    void info(String message, Throwable t);
    void debug(String message, Throwable t);
}
