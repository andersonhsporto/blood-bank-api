package com.api.bloodbankapi.screening.dto;

import com.api.bloodbankapi.commons.enums.ScreeningStatus;

import java.time.LocalDateTime;

public record ProtocolResponse(String protocol,
                               LocalDateTime date,
                               ScreeningStatus status,
                               String observation) {
}
