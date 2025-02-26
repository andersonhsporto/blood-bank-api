package com.api.bloodbankapi.screening.dto;

import java.time.LocalDateTime;

public record ScreeningDTO(
    String id,
    String donorName,
    String protocol,
    LocalDateTime date,
    String status,
    String observation,
    boolean answeredAllQuestions
) {
}
