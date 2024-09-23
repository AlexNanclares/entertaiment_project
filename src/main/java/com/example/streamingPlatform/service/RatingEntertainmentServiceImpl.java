package com.example.streamingPlatform.service;

import com.example.streamingPlatform.exceptions.BadRequestException;
import com.example.streamingPlatform.jwt.JwtService;
import com.example.streamingPlatform.persistence.entity.Entertainment;
import com.example.streamingPlatform.persistence.entity.RatingEntertainment;
import com.example.streamingPlatform.persistence.repository.EntertaimentRepository;
import com.example.streamingPlatform.persistence.repository.RatingEntertainmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingEntertainmentServiceImpl implements RatingEntertainmentService {

    private final EntertaimentRepository entertaimentRepository;
    private final RatingEntertainmentRepository ratingEntertainmentRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public void updateViewByUser(String entertaiment, String token) {

        Integer idUser = jwtService.getIdUserFromToken(token);
        Optional<Entertainment> entertainment = entertaimentRepository.getEntertaimentByName(entertaiment);

        if(idUser == null || idUser == 0){
            throw new BadRequestException("El token no es valido.");
        }

        if(entertainment.isEmpty()){
            throw new BadRequestException("La serie/pelicula no existe!");
        }

        Integer idEntertaiment = entertainment.get().getId();

        Optional<RatingEntertainment> rating = ratingEntertainmentRepository.getRatingByRelations(idUser, idEntertaiment);

        if(rating.isEmpty()){
            ratingEntertainmentRepository.saveRating(idUser, idEntertaiment);
        } else {
            if(rating.get().getView()){
                throw new BadRequestException("La serie/pelicula ya habia sido marcada como vista.");
            }
        }

        ratingEntertainmentRepository.updateRatingByView(true, idUser, idEntertaiment);
        //Se actualiza el numero de vistas de la pelicula/serie
        entertaimentRepository.updateNumberViewsEntertaiment(entertainment.get().getNumberViews() + 1, idEntertaiment);
    }

    @Override
    @Transactional
    public void updatePunctuationByUser(String entertaiment, Integer punctuation, String token) {

        Integer idUser = jwtService.getIdUserFromToken(token);
        Optional<Entertainment> entertainment = entertaimentRepository.getEntertaimentByName(entertaiment);

        if(idUser == null || idUser == 0){
            throw new BadRequestException("El token no es valido.");
        }

        if(entertainment.isEmpty()){
            throw new BadRequestException("La serie/pelicula no existe!");
        }

        Integer idEntertaiment = entertainment.get().getId();

        Optional<RatingEntertainment> rating = ratingEntertainmentRepository.getRatingByRelations(idUser, idEntertaiment);

        if(rating.isEmpty()){
            ratingEntertainmentRepository.saveRating(idUser, idEntertaiment);
        } else {
            if(rating.get().getPunctuation() != 0){
                throw new BadRequestException("La serie/pelicula ya habia sido puntuada.");
            }
        }

        ratingEntertainmentRepository.updateRatingByPunctuation(punctuation, idUser, idEntertaiment);

        //Se actualiza el promedio de la pelicula / serie
        Double score = (double) (ratingEntertainmentRepository.getSumEntertaimentPunctuated(idEntertaiment))/ratingEntertainmentRepository.getCounterEntertaimentPunctuated(idEntertaiment);

        entertaimentRepository.updateScoreEntertaiment(score, idEntertaiment);
    }
}
