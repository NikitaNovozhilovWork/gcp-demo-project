package com.nikitanov.demo.gcp.demoproject.chatwall.services;

import com.nikitanov.demo.gcp.demoproject.chatwall.dao.GitterMessageRepository;
import com.nikitanov.demo.gcp.demoproject.chatwall.models.dao.GitterMessge;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GitterMessageService {

    private final GitterMessageRepository gitterMessageRepository;

    public List<GitterMessge> getPreviousByDate(Date from, int size) {
        return gitterMessageRepository.findBySentLessThan(from, PageRequest.of(0, size, Sort.by("sent").descending()));
    }

}
