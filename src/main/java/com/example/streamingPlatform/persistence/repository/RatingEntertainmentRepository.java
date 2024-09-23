package com.example.streamingPlatform.persistence.repository;

import com.example.streamingPlatform.persistence.entity.RatingEntertainment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingEntertainmentRepository extends JpaRepository<RatingEntertainment, Integer> {

    @Query(value = "SELECT * FROM streaming_platform.rating_entertainment WHERE id_user = :idUser AND id_entertaiment = :idEntertaiment LIMIT 1;", nativeQuery = true)
    Optional<RatingEntertainment> getRatingByRelations(@Param("idUser") Integer idUser, @Param("idEntertaiment") Integer idEntertaiment);

    @Modifying
    @Query(value = "INSERT INTO streaming_platform.rating_entertainment (id_user, id_entertaiment) VALUES (:idUser, :idEntertaiment);", nativeQuery = true)
    void saveRating(@Param("idUser") Integer idUser, @Param("idEntertaiment") Integer idEntertaiment);

    @Modifying
    @Query(value = "UPDATE streaming_platform.rating_entertainment SET view = :view WHERE id_user = :idUser AND id_entertaiment = :idEntertaiment;", nativeQuery = true)
    void updateRatingByView(@Param("view") Boolean view, @Param("idUser") Integer idUser, @Param("idEntertaiment") Integer idEntertaiment);

    @Modifying
    @Query(value = "UPDATE streaming_platform.rating_entertainment SET punctuation = :punctuation WHERE id_user = :idUser AND id_entertaiment = :idEntertaiment;", nativeQuery = true)
    void updateRatingByPunctuation(@Param("punctuation") Integer punctuation, @Param("idUser") Integer idUser, @Param("idEntertaiment") Integer idEntertaiment);

    @Query(value = "SELECT SUM(punctuation) AS SUM_PUNCTUATIONS FROM streaming_platform.rating_entertainment WHERE id_entertaiment = :idEntertaiment AND punctuation != 0;", nativeQuery = true)
    Integer getSumEntertaimentPunctuated(@Param("idEntertaiment") Integer idEntertaiment);

    @Query(value = "SELECT COUNT(*) AS ENTERTAIMENT_PUNCTUATED FROM streaming_platform.rating_entertainment WHERE id_entertaiment = :idEntertaiment AND punctuation != 0;", nativeQuery = true)
    Integer getCounterEntertaimentPunctuated(@Param("idEntertaiment") Integer idEntertaiment);


}
