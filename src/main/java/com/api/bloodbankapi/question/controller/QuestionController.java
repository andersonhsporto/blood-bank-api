package com.api.bloodbankapi.question.controller;

import com.api.bloodbankapi.question.domain.QuestionService;
import com.api.bloodbankapi.question.entity.QuestionRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/question")
@AllArgsConstructor
@Log4j2
public class QuestionController {

    private final QuestionService service;

    @PostMapping()
    public void createQuestion(@RequestBody QuestionRequest questionRequest) {
        log.info("Creating question: {}", questionRequest);
        service.save(questionRequest);
    }

    @GetMapping("/{code}")
    public QuestionRequest getQuestionByCode(@PathVariable String code) {
        log.info("Finding question by code: {}", code);
        return service.findByCode(code);
    }

    @GetMapping()
    public List<QuestionRequest> getAllQuestions() {
        log.info("Finding all questions");
        return service.findAll();
    }

    @GetMapping("/paging")
    public List<QuestionRequest> getQuestionsPage(@RequestParam int page, @RequestParam int size) {
        log.info("Finding questions page: {} size: {}", page, size);
        return service.findPage(page, size);
    }

    @PutMapping("/{code}")
    public void updateQuestion(@PathVariable String code, @RequestBody QuestionRequest questionRequest) {
        log.info("Updating question: {}", questionRequest);
        service.update(code, questionRequest);
    }

    @PutMapping("/activate/{code}")
    public void activateQuestion(@PathVariable String code, @RequestParam boolean active) {
        log.info("Changing activation of question: {}", code);
        service.activate(code, active);
    }
}
