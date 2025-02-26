package com.api.bloodbankapi.question.controller;

import com.api.bloodbankapi.question.domain.QuestionService;
import com.api.bloodbankapi.question.entity.QuestionDTO;
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
    public void createQuestion(@RequestBody QuestionDTO questionDTO) {
        log.info("Creating question: {}", questionDTO);
        service.save(questionDTO);
    }

    @GetMapping("/{code}")
    public QuestionDTO getQuestionByCode(@PathVariable String code) {
        log.info("Finding question by code: {}", code);
        return service.findByCode(code);
    }

    @GetMapping()
    public List<QuestionDTO> getAllQuestions() {
        log.info("Finding all questions");
        return service.findAll();
    }

    @GetMapping("/paging")
    public List<QuestionDTO> getQuestionsPage(@RequestParam int page, @RequestParam int size) {
        log.info("Finding questions page: {} size: {}", page, size);
        return service.findPage(page, size);
    }

    @PutMapping("/{code}")
    public void updateQuestion(@PathVariable String code, @RequestBody QuestionDTO questionDTO) {
        log.info("Updating question: {}", questionDTO);
        service.update(code, questionDTO);
    }

    @PutMapping("/activate/{code}")
    public void activateQuestion(@PathVariable String code, @RequestParam boolean active) {
        log.info("Changing activation of question: {}", code);
        service.activate(code, active);
    }
}
