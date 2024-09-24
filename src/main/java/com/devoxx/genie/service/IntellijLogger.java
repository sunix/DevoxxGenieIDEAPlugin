package com.devoxx.genie.service;

public class IntellijLogger implements Logger {
    private final com.intellij.openapi.diagnostic.Logger logger;

    public IntellijLogger(Class<?> clazz) {
        this.logger = com.intellij.openapi.diagnostic.Logger.getInstance(clazz);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void trace(String message) {
        logger.trace(message);
    }

    @Override
    public void error(String message, Throwable t) {
        logger.error(message, t);
    }

    @Override
    public void warn(String message, Throwable t) {
        logger.warn(message, t);
    }

    @Override
    public void info(String message, Throwable t) {
        logger.info(message, t);
    }

    @Override
    public void debug(String message, Throwable t) {
        logger.debug(message, t);
    }




    
}
