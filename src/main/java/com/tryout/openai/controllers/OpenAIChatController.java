package com.tryout.openai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tryout.openai.dto.OpenAIRequest;
import com.tryout.openai.dto.OpenAIResponse;
import com.tryout.openai.dto.UserAIPrompt;

@RestController
public class OpenAIChatController {

    @Qualifier("restTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.apiUrl}")
    private String apiUrl;

    @PostMapping("/chat")
    @ResponseBody
    public ResponseEntity<OpenAIResponse> chatwithme(
            @RequestBody UserAIPrompt userAIPrompt) {
        OpenAIRequest request = new OpenAIRequest(model, userAIPrompt.getPrompt());
        OpenAIResponse response = restTemplate.postForObject(apiUrl, request,
                OpenAIResponse.class);

        if (response == null || response.getChoices() == null ||
                response.getChoices().isEmpty()) {
            return new ResponseEntity<OpenAIResponse>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<OpenAIResponse>(response, HttpStatus.OK);
    }
}