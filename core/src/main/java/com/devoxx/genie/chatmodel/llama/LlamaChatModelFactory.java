package com.devoxx.genie.chatmodel.llama;

import com.devoxx.genie.chatmodel.ChatModelFactory;
import com.devoxx.genie.model.ChatModel;
import com.devoxx.genie.model.LanguageModel;
import com.devoxx.genie.model.enumarations.ModelProvider;
import com.devoxx.genie.service.DevoxxGenieServiceProvider;
import com.devoxx.genie.service.DevoxxGenieSettingsService;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.localai.LocalAiChatModel;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LlamaChatModelFactory implements ChatModelFactory {

    @Override
    public ChatLanguageModel createChatModel(@NotNull ChatModel chatModel) {
        return LocalAiChatModel.builder()
            .baseUrl(DevoxxGenieSettingsService.getInstance().getLlamaCPPUrl())
            .modelName(chatModel.getModelName())
            .temperature(chatModel.getTemperature())
            .topP(chatModel.getTopP())
            .maxRetries(chatModel.getMaxRetries())
            .timeout(Duration.ofSeconds(chatModel.getTimeout()))
            .build();
    }

    @Override
    public List<LanguageModel> getModels() {
        LanguageModel lmStudio = LanguageModel.builder()
            .provider(ModelProvider.LLaMA)
            .modelName(TEST_MODEL)
            .displayName(TEST_MODEL)
            .inputCost(0)
            .outputCost(0)
            .contextWindow(8000)
            .apiKeyUsed(false)
            .build();

        List<LanguageModel> modelNames = new ArrayList<>();
        modelNames.add(lmStudio);
        return modelNames;
    }
}

