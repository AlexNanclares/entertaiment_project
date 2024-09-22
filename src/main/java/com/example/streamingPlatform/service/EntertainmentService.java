package com.example.streamingPlatform.service;

import com.example.streamingPlatform.persistence.entity.Entertainment;

import java.util.List;

public interface EntertainmentService {
    Entertainment getRandomEntertaiment();
    List<Entertainment> getEntertaimentByOrder(String typeOrder);
    List<Entertainment> getEntertaimentByFilter(String typeFilter, String filterValue);
}
