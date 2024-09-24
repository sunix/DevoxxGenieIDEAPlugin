package com.devoxx.genie.service;

import java.util.List;

import com.devoxx.genie.model.request.ChatMessageContext;

import dev.langchain4j.data.message.ChatMessage;

public interface ChatMemoryService {
    static void hello() {
        System.out.println("Hello from ChatMemoryService");
    }
    
    static ChatMemoryService getInstance() {
        return DevoxxGenieServiceProvider.getServiceProvider().getChatMemoryService();
    }

    void init();
    void clear();
    void add(ChatMessage message);
    void remove(ChatMessageContext chatMessageContext);
    void removeLast();
    List<ChatMessage> messages();
    boolean isEmpty();
    void onChatMemorySizeChanged(int newSize);



}
