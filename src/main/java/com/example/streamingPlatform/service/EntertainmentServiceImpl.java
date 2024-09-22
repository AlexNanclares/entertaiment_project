package com.example.streamingPlatform.service;

import com.example.streamingPlatform.persistence.entity.Entertainment;
import com.example.streamingPlatform.persistence.repository.EntertaimentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntertainmentServiceImpl implements EntertainmentService {

    private final EntertaimentRepository entertaimentRepository;

    @Override
    public Entertainment getRandomEntertaiment() {
        return entertaimentRepository.getRandomEntertaiment();
    }

    @Override
    public List<Entertainment> getEntertaimentByOrder(String typeOrder) {

        List<Entertainment> result = new ArrayList<>();

        if(typeOrder.equals("nombre")){
            result = entertaimentRepository.getEntertaimentsOrderByName();
        } else if(typeOrder.equals("tipo")){
            result = entertaimentRepository.getEntertaimentsOrderByType();
        } else if(typeOrder.equals("genero")){
            result = entertaimentRepository.getEntertaimentsOrderByGender();
        } else if(typeOrder.equals("puntaje")){
            result = entertaimentRepository.getEntertaimentsOrderByScore();
        }

        return  result;
    }

    @Override
    public List<Entertainment> getEntertaimentByFilter(String typeFilter, String filterValue) {
        List<Entertainment> result = new ArrayList<>();

        if(typeFilter.equals("nombre")){
            result = entertaimentRepository.getEntertaimentsFilterByName(filterValue);
        }else if(typeFilter.equals("tipo")){
            result = entertaimentRepository.getEntertaimentsFilterByType(filterValue);
        } else if(typeFilter.equals("genero")){
            result = entertaimentRepository.getEntertaimentsFilterByGender(filterValue);
        }

        return result;
    }
}
