package com.nikitanov.demo.gcp.demoproject.chathistory.services;

import com.google.cloud.Timestamp;
import com.nikitanov.demo.gcp.demoproject.chathistory.dao.GitterMessageRepository;
import com.nikitanov.demo.gcp.demoproject.chathistory.models.dao.GitterMessge;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service to get historical messages from repository
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GitterMessageService {

    private final GitterMessageRepository gitterMessageRepository;

    public List<GitterMessge> getPreviousByDate(Date from, int size) {
        return gitterMessageRepository.findBySentLessThan(Timestamp.of(from), PageRequest.of(0, size, Sort.by("sent").descending()));
    }

}
