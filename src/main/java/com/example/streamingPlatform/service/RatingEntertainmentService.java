package com.example.streamingPlatform.service;

public interface RatingEntertainmentService {
    void updateViewByUser(String entertaiment, String token);
    void updatePunctuationByUser(String entertaiment, Integer punctuation, String token);
}
