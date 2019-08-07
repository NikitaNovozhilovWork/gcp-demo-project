package com.nikitanov.demo.gcp.demoproject.dao;


import com.nikitanov.demo.gcp.demoproject.models.dao.GitterMessge;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

@Repository
public class GitterMessageRepository {

    private Map<ZonedDateTime, GitterMessge> messagesMap = new ConcurrentSkipListMap<>(Comparator.reverseOrder());

    public void save(GitterMessge message) {
        messagesMap.put(message.getSent(), message);
    }

    public List<GitterMessge> findBySentLessThan(final ZonedDateTime from, final int size) {
        return messagesMap
                .entrySet()
                .stream()
                .filter(e -> e.getKey().isBefore(from))
                .limit(size)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}
