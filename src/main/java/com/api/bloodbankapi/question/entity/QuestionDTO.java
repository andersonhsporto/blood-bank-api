package com.api.bloodbankapi.question.entity;

public record QuestionDTO(
        String code,
        String questionText,
        boolean mandatory,
        boolean active
) {
    public static Question toEntity(QuestionDTO questionDTO) {
        return Question.builder()
                .code(questionDTO.code())
                .questionText(questionDTO.questionText())
                .mandatory(questionDTO.mandatory())
                .active(true)
                .build();
    }

    public static QuestionDTO fromEntity(Question entity) {
        return new QuestionDTO(
                entity.getCode(),
                entity.getQuestionText(),
                entity.isMandatory(),
                entity.isActive()
        );
    }
}
