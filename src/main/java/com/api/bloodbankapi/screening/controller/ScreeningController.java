package com.api.bloodbankapi.screening.controller;

import com.api.bloodbankapi.screening.dto.ProtocolDTO;
import com.api.bloodbankapi.screening.dto.ScreeningDTO;
import com.api.bloodbankapi.screening.dto.ScreeningQuestionDTO;
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
    public ProtocolDTO createScreening(@PathVariable String donorDocumentId) {
        log.info("Creating screening: {}", donorDocumentId);
        return service.createScreening(donorDocumentId);
    }

    @GetMapping("/{protocol}")
    public ScreeningDTO findScreeningByProtocol(@PathVariable String protocol) {
        log.info("Finding screening by protocol: {}", protocol);
        return service.findScreeningByProtocol(protocol);
    }

    @GetMapping("/active")
    public List<ScreeningDTO> findActiveScreenings() {
        log.info("Finding active screenings");
        return service.findActiveScreenings();
    }

    @GetMapping("/inactive")
    public List<ScreeningDTO> findInactiveScreenings() {
        log.info("Finding inactive screenings");
        return service.findInactiveScreenings();
    }

    @GetMapping("/paging")
    public List<ScreeningDTO> getScreeningsPage(@RequestParam int page, @RequestParam int size) {
        log.info("Finding screenings page: {} size: {}", page, size);
        return service.findPage(page, size);
    }

    @GetMapping("/question/{protocol}")
    public List<ScreeningQuestionDTO> getScreeningQuestions(@PathVariable @NotNull String protocol) {
        log.info("Finding screening questions by protocol: {}", protocol);
        return service.findScreeningQuestions(protocol);
    }

}

