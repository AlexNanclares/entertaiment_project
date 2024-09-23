package com.example.streamingPlatform.controller;

import com.example.streamingPlatform.DTO.GenericResponse;
import com.example.streamingPlatform.service.RatingEntertainmentService;
import com.example.streamingPlatform.util.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingEntertainmentController {

    private final RatingEntertainmentService ratingEntertainmentService;

    @PutMapping(value = "/view")
    public ResponseEntity<GenericResponse> updateViewByUser(@RequestBody Map<String, Object> params, @RequestHeader("Authorization") String token){

        String entertaiment = (String) params.get("entertaiment");

        if(entertaiment == null || entertaiment.equals("")){
            return new ResponseEntity<>(new GenericResponse(400, "La serie/pelicula es requerida.", ""), HttpStatus.BAD_REQUEST);
        }

        ratingEntertainmentService.updateViewByUser(entertaiment, token.substring(7));

        return ResponseEntity.ok(new GenericResponse(200, "Se ha actualizado la visualización.", ""));
    }

    @PutMapping(value = "/punctuation")
    public ResponseEntity<GenericResponse> updatePunctuationByUser(@RequestBody Map<String, Object> params, @RequestHeader("Authorization") String token){

        String entertaiment = (String) params.get("entertaiment");
        Integer punctuation = (Integer) params.get("punctuation");

        if(Utilities.isNullOrEmpty(entertaiment) || Utilities.isNullOrEmpty(punctuation)){
            return new ResponseEntity<>(new GenericResponse(400, "Parametros requeridos.", null), HttpStatus.BAD_REQUEST);
        }

        if(punctuation <= 0 || punctuation > 5){
            return new ResponseEntity<>(new GenericResponse(400, "La puntuación debe estar dentro del rango 1 - 5", null), HttpStatus.BAD_REQUEST);
        }

        ratingEntertainmentService.updatePunctuationByUser(entertaiment, punctuation, token.substring(7));

        return ResponseEntity.ok(new GenericResponse(200, "Se ha actualizado la visualización.", ""));
    }
}
