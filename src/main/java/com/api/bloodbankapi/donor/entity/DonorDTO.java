package com.api.bloodbankapi.donor.entity;

import java.util.List;

public record DonorDTO(String name,

                       String documentId,

                       String email,

                       String contactNumber,

                       String gender,

                       String emergencyContactName,

                       String emergencyContactNumber,

                       String observation,

                       Boolean isTwins,

                       List<String> twinsNames) {

    public static DonorDTO fromEntity(Donor donor) {
        return new DonorDTO(
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

    public static Donor toEntity(DonorDTO donorDTO) {
        return Donor.builder()
                .name(donorDTO.name())
                .documentId(donorDTO.documentId())
                .email(donorDTO.email())
                .contactNumber(donorDTO.contactNumber())
                .gender(donorDTO.gender())
                .emergencyContactName(donorDTO.emergencyContactName())
                .emergencyContactNumber(donorDTO.emergencyContactNumber())
                .observation(donorDTO.observation())
                .isTwins(donorDTO.isTwins())
                .twinsNames(donorDTO.twinsNames())
                .build();
    }


}
