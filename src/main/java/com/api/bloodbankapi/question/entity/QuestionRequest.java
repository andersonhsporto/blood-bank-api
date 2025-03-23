package com.api.bloodbankapi.question.entity;

public record QuestionRequest(
        String code,
        String questionText,
        boolean mandatory,
        boolean active
) {
    public static Question toEntity(QuestionRequest questionRequest) {
        return Question.builder()
                .code(questionRequest.code())
                .questionText(questionRequest.questionText())
                .mandatory(questionRequest.mandatory())
                .active(true)
                .build();
    }

    public static QuestionRequest fromEntity(Question entity) {
        return new QuestionRequest(
                entity.getCode(),
                entity.getQuestionText(),
                entity.isMandatory(),
                entity.isActive()
        );
    }
}
