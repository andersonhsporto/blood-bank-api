package com.api.bloodbankapi.question.domain;

import com.api.bloodbankapi.commons.exception.QuestionDuplicatedCustomException;
import com.api.bloodbankapi.commons.exception.QuestionNotFoundCustomException;
import com.api.bloodbankapi.question.entity.Question;
import com.api.bloodbankapi.question.entity.QuestionRequest;
import com.api.bloodbankapi.question.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class QuestionService {

    private final QuestionRepository jpaRepository;

    public void save(QuestionRequest questionRequest) {
        Question entity = QuestionRequest.toEntity(questionRequest);

        if (jpaRepository.existsByCodeAndQuestionText(entity.getCode(), entity.getQuestionText())) {
            throw new QuestionDuplicatedCustomException();
        }

        jpaRepository.save(entity);
    }

    public QuestionRequest findByCode(String code) {
        Question entity = jpaRepository.findByCode(code)
                .orElseThrow(() -> new QuestionNotFoundCustomException());

        return QuestionRequest.fromEntity(entity);
    }

    public List<QuestionRequest> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(QuestionRequest::fromEntity)
                .toList();
    }

    public List<QuestionRequest> findPage(int page, int size) {
        Page<Question> questionPage = jpaRepository.findAll(PageRequest.of(page, size, Sort.by("code")));

        return questionPage.stream()
                .map(QuestionRequest::fromEntity)
                .toList();
    }

    public void update(String code, QuestionRequest questionRequest) {
        Question question = jpaRepository.findByCode(code)
                .orElseThrow(() -> new QuestionNotFoundCustomException());

        Question entity = QuestionRequest.toEntity(questionRequest);

        question.updateFromEntity(entity);
        log.info("Updating question: {}", question);
        jpaRepository.save(question);
    }

    public void activate(String code, boolean active) {
        Question question = jpaRepository.findByCode(code)
                .orElseThrow(() -> new QuestionNotFoundCustomException());

        question.setActive(active);
        log.info("Change question: {}", question);
        jpaRepository.save(question);
    }
}
