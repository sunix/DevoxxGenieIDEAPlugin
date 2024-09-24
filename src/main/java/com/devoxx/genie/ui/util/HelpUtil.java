package com.devoxx.genie.ui.util;

import com.devoxx.genie.service.DevoxxGenieServiceProvider;
import com.devoxx.genie.service.DevoxxGenieSettingsService;

import org.jetbrains.annotations.NotNull;

import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HelpUtil {

    private HelpUtil() {
    }

    public static @NotNull String getHelpMessage(@NotNull ResourceBundle resourceBundle) {
        return "<html><body style='width: 300px; font-family: Arial, sans-serif; font-size: 12px;'>" +
            "<h3>Available commands:</h3>" +
            "<ul>" +
            getCustomPromptCommands() +
            "</ul></body></html>";
    }

    public static @NotNull String getCustomPromptCommands() {
        return DevoxxGenieSettingsService.getInstance()
            .getCustomPrompts()
            .stream()
            .map(customPrompt -> "<li>/" + customPrompt.getName() + " : " + customPrompt.getPrompt() + "</li>")
            .collect(Collectors.joining());
    }
}
