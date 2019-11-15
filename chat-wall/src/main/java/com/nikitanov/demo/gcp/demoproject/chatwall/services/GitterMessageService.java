package com.nikitanov.demo.gcp.demoproject.chatwall.services;

import com.nikitanov.demo.gcp.demoproject.chatwall.dao.GitterMessageRepository;
import com.nikitanov.demo.gcp.demoproject.chatwall.models.dao.GitterMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service to get historical messages from repository
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GitterMessageService {

    private final GitterMessageRepository gitterMessageRepository;

    public List<GitterMessage> getPreviousByDate(ZonedDateTime from, int size) {
        return gitterMessageRepository.findBySentLessThan(from, PageRequest.of(0, size, Sort.by("id").descending()));
    }

}
