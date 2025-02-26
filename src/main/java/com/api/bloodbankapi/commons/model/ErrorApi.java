package com.api.bloodbankapi.commons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorApi {

    private String message;

    private String code;

    private OffsetDateTime timestamp;

    public ErrorApi(String message, String code) {
        this.message = message;
        this.code = code;
        this.timestamp = OffsetDateTime.now();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ErrorApi \n{ \n");
        sb.append("\tmessage: ").append(message).append(", \n");
        sb.append("\tcode: ").append(code).append(", \n");
        sb.append("\ttimestamp: ").append(timestamp).append(", \n");
        sb.append("} \n");

        return sb.toString();
    }
}
