package com.api.bloodbankapi.question.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(nullable = false)
    private String questionText;

    @Column(nullable = false)
    private boolean mandatory;

    @Column(nullable = false)
    private boolean active;

    @CreationTimestamp
    private Instant createdOn;

    @UpdateTimestamp
    private Instant lastUpdatedOn;

    @Version
    private int version;

    public void updateFromEntity(Question entity) {
        this.code = entity.getCode();
        this.questionText = entity.getQuestionText();
        this.mandatory = entity.isMandatory();
        this.active = entity.isActive();
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
