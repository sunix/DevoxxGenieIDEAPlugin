package com.devoxx.genie.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatModel {

    private String baseUrl;
    private String modelName;
    private double temperature = Constant.TEMPERATURE;
    private double topP = Constant.TOP_P;
    private int maxTokens = Constant.MAX_OUTPUT_TOKENS;
    private int maxRetries = Constant.MAX_RETRIES;
    private int timeout = Constant.TIMEOUT;
}
