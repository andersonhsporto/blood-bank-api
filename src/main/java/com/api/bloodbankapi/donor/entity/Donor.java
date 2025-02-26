package com.api.bloodbankapi.donor.entity;

import com.api.bloodbankapi.screening.entity.Screening;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "DOCUMENT_ID", unique = true)
    private String documentId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "EMERGENCY_NAME")
    private String emergencyContactName;

    @Column(name = "EMERGENCY_NUMBER")
    private String emergencyContactNumber;

    @Column(name = "OBSERVATION")
    private String observation;

    @Column(name = "IS_TWINS")
    private boolean isTwins;

    @ElementCollection
    private List<String> twinsNames;

    @Column(name = "SCREENINGS", nullable = true)
    @OneToMany(mappedBy = "donor")
    private List<Screening> screenings;

    @CreationTimestamp
    private Instant createdOn;

    @UpdateTimestamp
    private Instant lastUpdatedOn;

    public void updateFromEntity(Donor donor) {
        this.name = donor.getName();
        this.email = donor.getEmail();
        this.contactNumber = donor.getContactNumber();
        this.gender = donor.getGender();
        this.emergencyContactName = donor.getEmergencyContactName();
        this.emergencyContactNumber = donor.getEmergencyContactNumber();
        this.observation = donor.getObservation();
        this.isTwins = donor.isTwins();
        this.twinsNames = donor.getTwinsNames();
    }



}



