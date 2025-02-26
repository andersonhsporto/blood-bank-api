package com.api.bloodbankapi.donor.domain;

import com.api.bloodbankapi.commons.exception.DonorDuplicatedCustomException;
import com.api.bloodbankapi.commons.exception.DonorNotFoundCustomException;
import com.api.bloodbankapi.donor.entity.Donor;
import com.api.bloodbankapi.donor.entity.DonorDTO;
import com.api.bloodbankapi.donor.repository.DonorPagingRepository;
import com.api.bloodbankapi.donor.repository.DonorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class DonorService {

    private final DonorRepository jpaRepository;

    private final DonorPagingRepository pagingRepository;

    public DonorDTO findById(Long id) {
        return jpaRepository.findById(id)
                .map(DonorDTO::fromEntity)
                .orElseThrow(() -> new DonorNotFoundCustomException());
    }

    public List<DonorDTO> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(DonorDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public void save(DonorDTO donorDTO) {
        Donor entity = DonorDTO.toEntity(donorDTO);

        if (jpaRepository.existsByDocumentIdAndName(entity.getDocumentId(), entity.getName())) {
            throw new DonorDuplicatedCustomException();
        }

        jpaRepository.save(entity);
    }

    public DonorDTO findByDocumentId(String documentId) {
        return jpaRepository.findByDocumentId(documentId)
                .map(DonorDTO::fromEntity)
                .orElseThrow(() -> new DonorNotFoundCustomException("Donor not found with documentId: " + documentId));
    }

    public List<DonorDTO> findPage(int page, int size) {
        Page<Donor> donorPage = pagingRepository.findAll(PageRequest.of(page, size, Sort.by("name")));

        return donorPage.stream()
                .map(DonorDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public void update(String documentId, DonorDTO donorDTO) {
        Donor donor = jpaRepository.findByDocumentId(documentId)
                .orElseThrow(() -> new DonorNotFoundCustomException("Donor not found with documentId: " + documentId));

        Donor entity = DonorDTO.toEntity(donorDTO);

        donor.updateFromEntity(entity);

        log.info("Donor updated: {}", donor.getName());
        jpaRepository.save(donor);
    }

    @Transactional
    public void delete(String documentId) {
        Donor donor = jpaRepository.findByDocumentId(documentId)
                .orElseThrow(() -> new DonorNotFoundCustomException("Donor not found with documentId: " + documentId));

        log.info("Donor deleted: {}", donor.getName());
        jpaRepository.delete(donor);
    }
}
