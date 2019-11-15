package com.nikitanov.demo.gcp.demoproject.services;

import com.nikitanov.demo.gcp.demoproject.dao.GitterMessageRepository;
import com.nikitanov.demo.gcp.demoproject.models.dao.GitterMessge;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service to get historical messages from repository
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GitterMessageService {

    private final GitterMessageRepository gitterMessageRepository;

    public List<GitterMessge> getPreviousByDate(ZonedDateTime from, int size) {
        return gitterMessageRepository.findBySentLessThan(from, size);
    }
}
