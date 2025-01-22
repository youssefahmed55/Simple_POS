package com.example.simplepos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModelResponse {
    private int status;
    private String message;
}