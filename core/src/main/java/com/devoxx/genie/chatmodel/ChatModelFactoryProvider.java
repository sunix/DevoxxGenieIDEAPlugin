package com.devoxx.genie.chatmodel;

import com.devoxx.genie.chatmodel.anthropic.AnthropicChatModelFactory;
import com.devoxx.genie.chatmodel.deepinfra.DeepInfraChatModelFactory;
import com.devoxx.genie.chatmodel.exo.ExoChatModelFactory;
import com.devoxx.genie.chatmodel.google.GoogleChatModelFactory;
import com.devoxx.genie.chatmodel.groq.GroqChatModelFactory;
import com.devoxx.genie.chatmodel.mistral.MistralChatModelFactory;
import com.devoxx.genie.chatmodel.ollama.OllamaChatModelFactory;
import com.devoxx.genie.chatmodel.openai.OpenAIChatModelFactory;
import com.devoxx.genie.model.enumarations.ModelProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class ChatModelFactoryProvider {

    /**
     * The chat language model factory suppliers.
     */
    private static final Map<String, Supplier<ChatModelFactory>> FACTORY_SUPPLIERS = Map.of(
        ModelProvider.Ollama.getName(), OllamaChatModelFactory::new,
        ModelProvider.Exo.getName(), ExoChatModelFactory::new,
        ModelProvider.OpenAI.getName(), OpenAIChatModelFactory::new,
        ModelProvider.Anthropic.getName(), AnthropicChatModelFactory::new,
        ModelProvider.Mistral.getName(), MistralChatModelFactory::new,
        ModelProvider.Groq.getName(), GroqChatModelFactory::new,
        ModelProvider.DeepInfra.getName(), DeepInfraChatModelFactory::new,
        ModelProvider.Google.getName(), GoogleChatModelFactory::new
    );

    /**
     * Get the factory by provider.
     * @param modelProvider the model provider
     * @return the factory
     */
    public static @NotNull Optional<ChatModelFactory> getFactoryByProvider(@NotNull String modelProvider) {
        return Optional.ofNullable(FACTORY_SUPPLIERS.get(modelProvider)).map(Supplier::get);
    }
}
