package com.devoxx.genie.service;

import java.util.List;

import com.devoxx.genie.model.CustomPrompt;
import com.devoxx.genie.model.LanguageModel;
import com.devoxx.genie.model.enumarations.ModelProvider;

public interface DevoxxGenieSettingsService {

    static DevoxxGenieSettingsService getInstance() {
        return DevoxxGenieServiceProvider.getServiceProvider()
                .getDevoxxGenieSettingsService();
    }

    List<CustomPrompt> getCustomPrompts();

    List<LanguageModel> getLanguageModels();

    String getOllamaModelUrl();

    String getLmstudioModelUrl();

    String getGpt4allModelUrl();

    String getJanModelUrl();

    String getExoModelUrl();

    String getOpenAIKey();

    String getMistralKey();

    String getAnthropicKey();

    String getGroqKey();

    String getDeepInfraKey();

    String getGeminiKey();

    Boolean getHideSearchButtonsFlag();

    String getGoogleSearchKey();

    String getGoogleCSIKey();

    String getTavilySearchKey();

    Integer getMaxSearchResults();

    String getSelectedProvider(String projectLocation);

    String getSelectedLanguageModel(String projectLocation);

    Boolean getStreamMode();

    Double getTemperature();

    Double getTopP();

    Integer getTimeout();

    Integer getMaxRetries();

    Integer getChatMemorySize();

    Integer getMaxOutputTokens();

    Boolean getAstMode();

    Boolean getAstParentClass();

    Boolean getAstClassReference();

    Boolean getAstFieldReference();

    String getSystemPrompt();

    String getTestPrompt();

    String getReviewPrompt();

    String getExplainPrompt();

    Boolean getExcludeJavaDoc();

    List<String> getExcludedDirectories();

    List<String> getIncludedFileExtensions();

    Integer getDefaultWindowContext();

    void setCustomPrompts(List<CustomPrompt> customPrompts);

    void setLanguageModels(List<LanguageModel> languageModels);

    void setOllamaModelUrl(String url);

    void setLmstudioModelUrl(String url);

    void setGpt4allModelUrl(String url);

    void setJanModelUrl(String url);

    void setExoModelUrl(String url);

    void setOpenAIKey(String key);

    void setMistralKey(String key);

    void setAnthropicKey(String key);

    void setGroqKey(String key);

    void setDeepInfraKey(String key);

    void setGeminiKey(String key);

    void setHideSearchButtonsFlag(Boolean flag);

    void setGoogleSearchKey(String key);

    void setGoogleCSIKey(String key);

    void setTavilySearchKey(String key);

    void setMaxSearchResults(Integer results);

    void setSelectedProvider(String projectLocation, String provider);

    void setSelectedLanguageModel(String projectLocation, String model);

    void setStreamMode(Boolean mode);

    void setTemperature(Double temperature);

    void setTopP(Double topP);

    void setTimeout(Integer timeout);

    void setMaxRetries(Integer retries);

    void setChatMemorySize(Integer size);

    void setMaxOutputTokens(Integer tokens);

    void setAstMode(Boolean mode);

    void setAstParentClass(Boolean parentClass);

    void setAstClassReference(Boolean classReference);

    void setAstFieldReference(Boolean fieldReference);

    void setSystemPrompt(String prompt);

    void setTestPrompt(String prompt);

    void setReviewPrompt(String prompt);

    void setExplainPrompt(String prompt);

    void setExcludeJavaDoc(Boolean exclude);

    void setExcludedDirectories(List<String> directories);

    void setIncludedFileExtensions(List<String> extensions);

    void setDefaultWindowContext(Integer context);

    void setModelCost(ModelProvider provider, String modelName, double inputCost, double outputCost);

    void setModelWindowContext(ModelProvider provider, String modelName, int windowContext);

    double getModelInputCost(ModelProvider provider, String modelName);

    String getLlamaCPPUrl();

    void setLlamaCPPUrl(String text);

}
