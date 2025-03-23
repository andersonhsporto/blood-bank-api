package com.api.bloodbankapi.screening.controller;

import com.api.bloodbankapi.screening.dto.ProtocolResponse;
import com.api.bloodbankapi.screening.dto.ScreeningResponse;
import com.api.bloodbankapi.screening.dto.ScreeningQuestionResponse;
import com.api.bloodbankapi.screening.service.ScreeningService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/screening")
@AllArgsConstructor
@Log4j2
public class ScreeningController {

    private final ScreeningService service;

    @PostMapping("/{donorDocumentId}")
    public ProtocolResponse createScreening(@PathVariable String donorDocumentId) {
        log.info("Creating screening: {}", donorDocumentId);
        return service.createScreening(donorDocumentId);
    }

    @GetMapping("/{protocol}")
    public ScreeningResponse findScreeningByProtocol(@PathVariable String protocol) {
        log.info("Finding screening by protocol: {}", protocol);
        return service.findScreeningByProtocol(protocol);
    }

    @GetMapping("/active")
    public List<ScreeningResponse> findActiveScreenings() {
        log.info("Finding active screenings");
        return service.findActiveScreenings();
    }

    @GetMapping("/inactive")
    public List<ScreeningResponse> findInactiveScreenings() {
        log.info("Finding inactive screenings");
        return service.findInactiveScreenings();
    }

    @GetMapping("/paging")
    public List<ScreeningResponse> getScreeningsPage(@RequestParam int page, @RequestParam int size) {
        log.info("Finding screenings page: {} size: {}", page, size);
        return service.findPage(page, size);
    }

    @GetMapping("/question/{protocol}")
    public List<ScreeningQuestionResponse> getScreeningQuestions(@PathVariable @NotNull String protocol) {
        log.info("Finding screening questions by protocol: {}", protocol);
        return service.findScreeningQuestions(protocol);
    }

}

