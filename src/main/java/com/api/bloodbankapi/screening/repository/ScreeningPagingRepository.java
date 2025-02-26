package com.api.bloodbankapi.screening.repository;

import com.api.bloodbankapi.screening.entity.Screening;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;

public interface ScreeningPagingRepository extends PagingAndSortingRepository<Screening, Long> {

}
