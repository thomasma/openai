package com.tryout.openai.dto;

import java.util.List;

import lombok.Data;

@Data
public class OpenAIResponse {
    private List<Choice> choices;
}
