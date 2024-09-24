package com.devoxx.genie;

import java.util.List;

import com.devoxx.genie.model.Constant;
import com.devoxx.genie.model.CustomPrompt;
import com.devoxx.genie.model.LanguageModel;
import com.devoxx.genie.model.enumarations.ModelProvider;
import com.devoxx.genie.service.DevoxxGenieSettingsService;
import com.devoxx.genie.service.Logger;
import com.devoxx.genie.service.MessageCreationService;
import com.devoxx.genie.service.ChatMemoryService;
import com.devoxx.genie.service.CliChatMemoryService;
import com.devoxx.genie.service.CliMessageCreationService;
import com.devoxx.genie.service.NotificationService;
import com.devoxx.genie.service.ProjectManager;
import com.devoxx.genie.service.DevoxxGenieServiceProvider;

public class CliServiceProvider implements DevoxxGenieServiceProvider {

    @Override
    public DevoxxGenieSettingsService getDevoxxGenieSettingsService() {
        return new DevoxxGenieSettingsService() {

            @Override
            public List<CustomPrompt> getCustomPrompts() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getCustomPrompts'");
            }

            @Override
            public List<LanguageModel> getLanguageModels() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getLanguageModels'");
            }

            @Override
            public String getOllamaModelUrl() {
                return Constant.OLLAMA_MODEL_URL;
            }

            @Override
            public String getLmstudioModelUrl() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getLmstudioModelUrl'");
            }

            @Override
            public String getGpt4allModelUrl() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getGpt4allModelUrl'");
            }

            @Override
            public String getJanModelUrl() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getJanModelUrl'");
            }

            @Override
            public String getExoModelUrl() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getExoModelUrl'");
            }

            @Override
            public String getOpenAIKey() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getOpenAIKey'");
            }

            @Override
            public String getMistralKey() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getMistralKey'");
            }

            @Override
            public String getAnthropicKey() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getAnthropicKey'");
            }

            @Override
            public String getGroqKey() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getGroqKey'");
            }

            @Override
            public String getDeepInfraKey() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getDeepInfraKey'");
            }

            @Override
            public String getGeminiKey() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getGeminiKey'");
            }

            @Override
            public Boolean getHideSearchButtonsFlag() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getHideSearchButtonsFlag'");
            }

            @Override
            public String getGoogleSearchKey() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getGoogleSearchKey'");
            }

            @Override
            public String getGoogleCSIKey() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getGoogleCSIKey'");
            }

            @Override
            public String getTavilySearchKey() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getTavilySearchKey'");
            }

            @Override
            public Integer getMaxSearchResults() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getMaxSearchResults'");
            }

            @Override
            public String getSelectedProvider(String projectLocation) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getSelectedProvider'");
            }

            @Override
            public String getSelectedLanguageModel(String projectLocation) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getSelectedLanguageModel'");
            }

            @Override
            public Boolean getStreamMode() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getStreamMode'");
            }

            @Override
            public Double getTemperature() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getTemperature'");
            }

            @Override
            public Double getTopP() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getTopP'");
            }

            @Override
            public Integer getTimeout() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getTimeout'");
            }

            @Override
            public Integer getMaxRetries() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getMaxRetries'");
            }

            @Override
            public Integer getChatMemorySize() {
                return 10;
            }

            @Override
            public Integer getMaxOutputTokens() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getMaxOutputTokens'");
            }

            @Override
            public Boolean getAstMode() {
                return false;
            }

            @Override
            public Boolean getAstParentClass() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getAstParentClass'");
            }

            @Override
            public Boolean getAstClassReference() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getAstClassReference'");
            }

            @Override
            public Boolean getAstFieldReference() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getAstFieldReference'");
            }

            @Override
            public String getSystemPrompt() {
                return Constant.SYSTEM_PROMPT;
            }

            @Override
            public String getTestPrompt() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getTestPrompt'");
            }

            @Override
            public String getReviewPrompt() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getReviewPrompt'");
            }

            @Override
            public String getExplainPrompt() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getExplainPrompt'");
            }

            @Override
            public Boolean getExcludeJavaDoc() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getExcludeJavaDoc'");
            }

            @Override
            public List<String> getExcludedDirectories() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getExcludedDirectories'");
            }

            @Override
            public List<String> getIncludedFileExtensions() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getIncludedFileExtensions'");
            }

            @Override
            public Integer getDefaultWindowContext() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getDefaultWindowContext'");
            }

            @Override
            public void setCustomPrompts(List<CustomPrompt> customPrompts) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setCustomPrompts'");
            }

            @Override
            public void setLanguageModels(List<LanguageModel> languageModels) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setLanguageModels'");
            }

            @Override
            public void setOllamaModelUrl(String url) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setOllamaModelUrl'");
            }

            @Override
            public void setLmstudioModelUrl(String url) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setLmstudioModelUrl'");
            }

            @Override
            public void setGpt4allModelUrl(String url) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setGpt4allModelUrl'");
            }

            @Override
            public void setJanModelUrl(String url) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setJanModelUrl'");
            }

            @Override
            public void setExoModelUrl(String url) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setExoModelUrl'");
            }

            @Override
            public void setOpenAIKey(String key) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setOpenAIKey'");
            }

            @Override
            public void setMistralKey(String key) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setMistralKey'");
            }

            @Override
            public void setAnthropicKey(String key) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setAnthropicKey'");
            }

            @Override
            public void setGroqKey(String key) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setGroqKey'");
            }

            @Override
            public void setDeepInfraKey(String key) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setDeepInfraKey'");
            }

            @Override
            public void setGeminiKey(String key) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setGeminiKey'");
            }

            @Override
            public void setHideSearchButtonsFlag(Boolean flag) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setHideSearchButtonsFlag'");
            }

            @Override
            public void setGoogleSearchKey(String key) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setGoogleSearchKey'");
            }

            @Override
            public void setGoogleCSIKey(String key) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setGoogleCSIKey'");
            }

            @Override
            public void setTavilySearchKey(String key) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setTavilySearchKey'");
            }

            @Override
            public void setMaxSearchResults(Integer results) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setMaxSearchResults'");
            }

            @Override
            public void setSelectedProvider(String projectLocation, String provider) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setSelectedProvider'");
            }

            @Override
            public void setSelectedLanguageModel(String projectLocation, String model) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setSelectedLanguageModel'");
            }

            @Override
            public void setStreamMode(Boolean mode) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setStreamMode'");
            }

            @Override
            public void setTemperature(Double temperature) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setTemperature'");
            }

            @Override
            public void setTopP(Double topP) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setTopP'");
            }

            @Override
            public void setTimeout(Integer timeout) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setTimeout'");
            }

            @Override
            public void setMaxRetries(Integer retries) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setMaxRetries'");
            }

            @Override
            public void setChatMemorySize(Integer size) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setChatMemorySize'");
            }

            @Override
            public void setMaxOutputTokens(Integer tokens) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setMaxOutputTokens'");
            }

            @Override
            public void setAstMode(Boolean mode) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setAstMode'");
            }

            @Override
            public void setAstParentClass(Boolean parentClass) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setAstParentClass'");
            }

            @Override
            public void setAstClassReference(Boolean classReference) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setAstClassReference'");
            }

            @Override
            public void setAstFieldReference(Boolean fieldReference) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setAstFieldReference'");
            }

            @Override
            public void setSystemPrompt(String prompt) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setSystemPrompt'");
            }

            @Override
            public void setTestPrompt(String prompt) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setTestPrompt'");
            }

            @Override
            public void setReviewPrompt(String prompt) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setReviewPrompt'");
            }

            @Override
            public void setExplainPrompt(String prompt) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setExplainPrompt'");
            }

            @Override
            public void setExcludeJavaDoc(Boolean exclude) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setExcludeJavaDoc'");
            }

            @Override
            public void setExcludedDirectories(List<String> directories) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setExcludedDirectories'");
            }

            @Override
            public void setIncludedFileExtensions(List<String> extensions) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setIncludedFileExtensions'");
            }

            @Override
            public void setDefaultWindowContext(Integer context) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setDefaultWindowContext'");
            }

            @Override
            public void setModelCost(ModelProvider provider, String modelName, double inputCost, double outputCost) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setModelCost'");
            }

            @Override
            public void setModelWindowContext(ModelProvider provider, String modelName, int windowContext) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setModelWindowContext'");
            }

            @Override
            public double getModelInputCost(ModelProvider provider, String modelName) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getModelInputCost'");
            }

            @Override
            public String getLlamaCPPUrl() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getLlamaCPPUrl'");
            }

            @Override
            public void setLlamaCPPUrl(String text) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setLlamaCPPUrl'");
            }
            
        };
    }

    @Override
    public NotificationService getNotificationService() {
        return new NotificationService() {

            @Override
            public void sendNotification(ProjectHandler project, String content) {
                System.out.println("Notification: " + content);
            }
            
        };
    }

    @Override
    public ProjectManager getProjectManager() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProjectManager'");
    }

    @Override
    public Logger getLogger(Class<?> clazz) {
        return new Logger() {

            @Override
            public void info(String message) {
                System.out.println("INFO: " + message);
            }

            @Override
            public void warn(String message) {
                System.out.println("WARN: " + message);
            }

            @Override
            public void debug(String message) {
                System.out.println("DEBUG: " + message);
            }
            
            @Override
            public void error(String message) {
                System.out.println("ERROR: " + message);
            }

            @Override
            public void trace(String message) {
                System.out.println("TRACE: " + message);
            }

            @Override
            public void info(String message, Throwable t) {
                System.out.println("INFO: " + message);
                t.printStackTrace();
            }

            @Override
            public void warn(String message, Throwable t) {
                System.out.println("WARN: " + message);
                t.printStackTrace();
            }

            @Override
            public void debug(String message, Throwable t) {
                System.out.println("DEBUG: " + message);
                t.printStackTrace();
            }

            @Override
            public void error(String message, Throwable t) {
                System.out.println("ERROR: " + message);
                t.printStackTrace();
            }

        };
    }

    @Override
    public MessageCreationService getMessageCreationService() {
        return CliMessageCreationService.getInstance();
    }

    @Override
    public ChatMemoryService getChatMemoryService() {
        return CliChatMemoryService.getInstance();
    }

    
    
}
