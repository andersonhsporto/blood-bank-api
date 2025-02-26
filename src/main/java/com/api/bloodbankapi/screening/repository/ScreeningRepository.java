package com.api.bloodbankapi.screening.repository;

import com.api.bloodbankapi.commons.enums.ScreeningStatus;
import com.api.bloodbankapi.screening.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    Optional<Screening> findByDonorDocumentId(String donorDocumentId);

    Optional<Screening> findByProtocol(String protocol);

    List<Screening> findByStatus(ScreeningStatus screeningStatus);

    List<Screening> findByStatusNotContaining(ScreeningStatus screeningStatus);
}
