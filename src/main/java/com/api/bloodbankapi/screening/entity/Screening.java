package com.api.bloodbankapi.screening.entity;

import com.api.bloodbankapi.commons.enums.ScreeningStatus;
import com.api.bloodbankapi.donor.entity.Donor;
import com.api.bloodbankapi.question.entity.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "PROTOCOL", nullable = false)
    private String protocol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ElementCollection
    @Column(name = "questions", nullable = false)
    private Map<Question, String> questions;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private ScreeningStatus status;

    private String observation;

    @CreationTimestamp
    private Instant createdOn;

    @UpdateTimestamp
    private Instant lastUpdatedOn;
}
