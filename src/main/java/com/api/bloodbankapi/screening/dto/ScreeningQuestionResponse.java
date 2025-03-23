package com.api.bloodbankapi.screening.dto;

import java.util.Map;

public record ScreeningQuestionResponse(
        Map<String, String> question,
        boolean answeredAllQuestions){
}
