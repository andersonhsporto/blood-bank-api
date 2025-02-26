package com.api.bloodbankapi.donor.repository;

import com.api.bloodbankapi.donor.entity.Donor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DonorPagingRepository extends PagingAndSortingRepository<Donor, Long> {
}
