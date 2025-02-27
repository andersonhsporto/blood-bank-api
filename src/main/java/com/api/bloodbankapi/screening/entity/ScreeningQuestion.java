package com.api.bloodbankapi.screening.entity;

import com.api.bloodbankapi.question.entity.Question;
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
public class ScreeningQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    private Boolean isAnswered = false;

    @Column(name = "answer")
    private String answer;

    @CreationTimestamp
    private Instant createdOn;

    @UpdateTimestamp
    private Instant lastUpdatedOn;

    public String getQuestionAndAnswer() {
        return question.getQuestionText() + " - " + answer;
    }
}
