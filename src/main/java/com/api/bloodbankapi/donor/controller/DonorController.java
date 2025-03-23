package com.api.bloodbankapi.donor.controller;

import com.api.bloodbankapi.commons.advice.CustomControllerAdvice;
import com.api.bloodbankapi.donor.entity.DonorRequest;
import com.api.bloodbankapi.donor.domain.DonorService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/donor")
@AllArgsConstructor
@Log4j2
public class DonorController extends CustomControllerAdvice {

    private final DonorService service;

    @PostMapping()
    public void createDonor(@RequestBody DonorRequest donorRequest) {
        log.info("Creating donor: {}", donorRequest);
        service.save(donorRequest);
    }

    @GetMapping("/{documentId}")
    public DonorRequest getDonorByDocumentId(@PathVariable String documentId) {
        log.info("Finding donor by id: {}", documentId);
        return service.findByDocumentId(documentId);
    }

    @GetMapping()
    public List<DonorRequest> getAllDonors() {
        log.info("Finding all donors");
        return service.findAll();
    }

    @GetMapping("/paging")
    public List<DonorRequest> getDonorsPage(@RequestParam int page, @RequestParam int size) {
        log.info("Finding donors page: {} size: {}", page, size);
        return service.findPage(page, size);
    }

    @PutMapping("/{documentId}")
    public void updateDonor(@PathVariable String documentId, @RequestBody DonorRequest donorRequest) {
        log.info("Updating donor: {}", donorRequest);
        service.update(documentId, donorRequest);
    }

    @DeleteMapping("/{documentId}")
    public void deleteDonor(@PathVariable String documentId) {
        log.info("Deleting donor by id: {}", documentId);
        service.delete(documentId);
    }

}
