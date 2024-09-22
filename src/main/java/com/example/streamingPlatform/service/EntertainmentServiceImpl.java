package com.example.streamingPlatform.service;

import com.example.streamingPlatform.persistence.entity.Entertainment;
import com.example.streamingPlatform.persistence.repository.EntertaimentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntertainmentServiceImpl implements EntertainmentService {

    private final EntertaimentRepository entertaimentRepository;

    @Override
    public Entertainment getRandomEntertaiment() {
        return entertaimentRepository.getRandomEntertaiment();
    }
}
