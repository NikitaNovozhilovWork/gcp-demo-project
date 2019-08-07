package com.nikitanov.demo.gcp.demoproject.dao;


import com.nikitanov.demo.gcp.demoproject.models.dao.GitterMessge;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface GitterMessageRepository extends PagingAndSortingRepository<GitterMessge, Long> {

    List<GitterMessge> findBySentLessThan(ZonedDateTime from, Pageable pageable);

}
