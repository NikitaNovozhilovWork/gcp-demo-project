package com.nikitanov.demo.gcp.demoproject.chatwall.dao;

import com.nikitanov.demo.gcp.demoproject.chatwall.models.dao.GitterMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface GitterMessageRepository extends PagingAndSortingRepository<GitterMessage, Long> {

    List<GitterMessage> findBySentLessThan(ZonedDateTime from, Pageable pageable);

}
