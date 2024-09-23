package com.example.streamingPlatform.persistence.repository;

import com.example.streamingPlatform.persistence.entity.Entertainment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntertaimentRepository extends JpaRepository<Entertainment, Integer> {

    @Query(value = "SELECT * FROM streaming_platform.entertainment ORDER BY RAND() LIMIT 1;", nativeQuery = true)
    Entertainment getRandomEntertaiment();

    @Query(value = "SELECT * FROM streaming_platform.entertainment ORDER BY name;", nativeQuery = true)
    List<Entertainment> getEntertaimentsOrderByName();

    @Query(value = "SELECT * FROM streaming_platform.entertainment ORDER BY type;", nativeQuery = true)
    List<Entertainment> getEntertaimentsOrderByType();

    @Query(value = "SELECT * FROM streaming_platform.entertainment ORDER BY gender;", nativeQuery = true)
    List<Entertainment> getEntertaimentsOrderByGender();

    @Query(value = "SELECT * FROM streaming_platform.entertainment ORDER BY score;", nativeQuery = true)
    List<Entertainment> getEntertaimentsOrderByScore();

    @Query(value = "SELECT * FROM streaming_platform.entertainment WHERE name = :name", nativeQuery = true)
    List<Entertainment> getEntertaimentsFilterByName(@Param("name") String name);

    @Query(value = "SELECT * FROM streaming_platform.entertainment WHERE type = :type", nativeQuery = true)
    List<Entertainment> getEntertaimentsFilterByType(@Param("type") String type);

    @Query(value = "SELECT * FROM streaming_platform.entertainment WHERE gender = :gender", nativeQuery = true)
    List<Entertainment> getEntertaimentsFilterByGender(@Param("gender") String gender);

    @Query(value = "SELECT * FROM streaming_platform.entertainment WHERE name = :name LIMIT 1;", nativeQuery = true)
    Optional<Entertainment> getEntertaimentByName(@Param("name") String name);

    @Modifying
    @Query(value = "UPDATE streaming_platform.entertainment SET score = :score WHERE id = :id;", nativeQuery = true)
    void updateScoreEntertaiment(@Param("score") Double score, @Param("id") Integer id);

    @Modifying
    @Query(value = "UPDATE streaming_platform.entertainment SET number_views = :numberViews WHERE id = :id;", nativeQuery = true)
    void updateNumberViewsEntertaiment(@Param("numberViews") Integer numberViews, @Param("id") Integer id);
}
