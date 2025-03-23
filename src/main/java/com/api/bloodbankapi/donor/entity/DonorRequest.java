package com.api.bloodbankapi.donor.entity;

import java.util.List;

public record DonorRequest(String name,

                           String documentId,

                           String email,

                           String contactNumber,

                           String gender,

                           String emergencyContactName,

                           String emergencyContactNumber,

                           String observation,

                           Boolean isTwins,

                           List<String> twinsNames) {

    public static DonorRequest fromEntity(Donor donor) {
        return new DonorRequest(
                donor.getName(),
                donor.getDocumentId(),
                donor.getEmail(),
                donor.getContactNumber(),
                donor.getGender(),
                donor.getEmergencyContactName(),
                donor.getEmergencyContactNumber(),
                donor.getObservation(),
                donor.isTwins(),
                donor.getTwinsNames()
        );
    }

    public static Donor toEntity(DonorRequest donorRequest) {
        return Donor.builder()
                .name(donorRequest.name())
                .documentId(donorRequest.documentId())
                .email(donorRequest.email())
                .contactNumber(donorRequest.contactNumber())
                .gender(donorRequest.gender())
                .emergencyContactName(donorRequest.emergencyContactName())
                .emergencyContactNumber(donorRequest.emergencyContactNumber())
                .observation(donorRequest.observation())
                .isTwins(donorRequest.isTwins())
                .twinsNames(donorRequest.twinsNames())
                .build();
    }


}
