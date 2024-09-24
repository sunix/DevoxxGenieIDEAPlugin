package com.devoxx.genie.chatmodel.ollama;

import com.devoxx.genie.model.ChatModel;
import com.devoxx.genie.service.DevoxxGenieServiceProvider;
import com.devoxx.genie.service.DevoxxGenieSettingsService;
import com.devoxx.genie.ui.settings.DevoxxGenieStateService;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OllamaChatModelFactoryTest {

    @Test
    void testCreateChatModel() {
        try (MockedStatic<DevoxxGenieServiceProvider> mockedSettings = Mockito.mockStatic(DevoxxGenieServiceProvider.class)) {
            // Setup the mock for SettingsState
            DevoxxGenieStateService mockSettingsState = mock(DevoxxGenieStateService.class);
            when(DevoxxGenieSettingsService.getInstance()).thenReturn(mockSettingsState);
            when(mockSettingsState.getOllamaModelUrl()).thenReturn("http://localhost:8080");

            // Instance of the class containing the method to be tested
            OllamaChatModelFactory factory = new OllamaChatModelFactory();

            // Create a dummy ChatModel
            ChatModel chatModel = new ChatModel();
            chatModel.setModelName("ollama");
            chatModel.setBaseUrl("http://localhost:8080");

            // Call the method
            ChatLanguageModel result = factory.createChatModel(chatModel);
            assertThat(result).isNotNull();
        }
    }
}
