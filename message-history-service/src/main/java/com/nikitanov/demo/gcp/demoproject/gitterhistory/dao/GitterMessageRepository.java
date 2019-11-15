package com.nikitanov.demo.gcp.demoproject.gitterhistory.dao;

import com.nikitanov.demo.gcp.demoproject.gitterhistory.models.dao.GitterMessge;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Spring Data repository to work with Cloud Datastore
 */
@Repository
public interface GitterMessageRepository extends PagingAndSortingRepository<GitterMessge, String> {

    List<GitterMessge> findBySentLessThan(Date from, Pageable pageable);

}
