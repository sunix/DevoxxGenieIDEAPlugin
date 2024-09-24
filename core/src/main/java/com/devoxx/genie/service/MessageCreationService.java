package com.devoxx.genie.service;

import org.jetbrains.annotations.NotNull;

import com.devoxx.genie.model.request.ChatMessageContext;

import dev.langchain4j.data.message.UserMessage;

public interface MessageCreationService {

    static MessageCreationService getInstance() {
        return DevoxxGenieServiceProvider.getServiceProvider().getMessageCreationService();
    }

    UserMessage createUserMessage(@NotNull ChatMessageContext chatMessageContext);
}
