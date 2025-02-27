package com.api.bloodbankapi.screening.dto;

import java.util.Map;

public record ScreeningQuestionDTO(
        Map<String, String> question,
        boolean answeredAllQuestions){
}
