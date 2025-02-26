package com.api.bloodbankapi.donor.repository;

import com.api.bloodbankapi.donor.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long>{
    boolean existsByDocumentIdAndName(String documentId, String name);

    Optional<Donor> findByDocumentId(String documentId);
}
