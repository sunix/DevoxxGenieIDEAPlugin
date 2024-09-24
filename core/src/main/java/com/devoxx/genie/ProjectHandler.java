package com.devoxx.genie;

public interface ProjectHandler {
    
    <T> T getAdaptedInstance(Class<T> adapteeClassName);


}
