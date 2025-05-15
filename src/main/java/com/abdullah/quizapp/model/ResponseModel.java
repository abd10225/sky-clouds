package com.abdullah.quizapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseModel {
    private Integer id;
    private String response;
}
