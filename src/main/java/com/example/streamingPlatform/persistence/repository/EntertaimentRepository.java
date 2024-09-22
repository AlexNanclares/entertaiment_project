package com.example.streamingPlatform.persistence.repository;

import com.example.streamingPlatform.persistence.entity.Entertainment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
