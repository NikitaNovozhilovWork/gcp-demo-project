package com.nikitanov.demo.gcp.demoproject.chathistory.dao;

import com.google.cloud.Timestamp;
import com.nikitanov.demo.gcp.demoproject.chathistory.models.dao.GitterMessge;
import org.springframework.cloud.gcp.data.spanner.repository.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data repository to work with Cloud Spanner
 */
@Repository
public interface GitterMessageRepository extends PagingAndSortingRepository<GitterMessge, String> {

    @Query("SELECT * FROM messages WHERE messages.sent < @from ORDER BY messages.sent DESC")
    List<GitterMessge> findBySentLessThan(@Param("from") Timestamp from, Pageable pageable);

}
