package com.api.bloodbankapi.question.domain;

import com.api.bloodbankapi.commons.exception.QuestionDuplicatedCustomException;
import com.api.bloodbankapi.commons.exception.QuestionNotFoundCustomException;
import com.api.bloodbankapi.question.entity.Question;
import com.api.bloodbankapi.question.entity.QuestionDTO;
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

    public void save(QuestionDTO questionDTO) {
        Question entity = QuestionDTO.toEntity(questionDTO);

        if (jpaRepository.existsByCodeAndQuestionText(entity.getCode(), entity.getQuestionText())) {
            throw new QuestionDuplicatedCustomException();
        }

        jpaRepository.save(entity);
    }

    public QuestionDTO findByCode(String code) {
        Question entity = jpaRepository.findByCode(code)
                .orElseThrow(() -> new QuestionNotFoundCustomException());

        return QuestionDTO.fromEntity(entity);
    }

    public List<QuestionDTO> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(QuestionDTO::fromEntity)
                .toList();
    }

    public List<QuestionDTO> findPage(int page, int size) {
        Page<Question> questionPage = jpaRepository.findAll(PageRequest.of(page, size, Sort.by("code")));

        return questionPage.stream()
                .map(QuestionDTO::fromEntity)
                .toList();
    }

    public void update(String code, QuestionDTO questionDTO) {
        Question question = jpaRepository.findByCode(code)
                .orElseThrow(() -> new QuestionNotFoundCustomException());

        Question entity = QuestionDTO.toEntity(questionDTO);

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
