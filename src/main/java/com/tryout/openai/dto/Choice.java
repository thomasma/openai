package com.tryout.openai.dto;

import lombok.Data;

@Data
public class Choice {
    private int index;
    private Message message;
}
