package com.example.streamingPlatform.persistence.repository;

import com.example.streamingPlatform.persistence.entity.Entertainment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EntertaimentRepository extends JpaRepository<Entertainment, Integer> {

    @Query(value = "SELECT * FROM streaming_platform.entertainment ORDER BY RAND() LIMIT 1;", nativeQuery = true)
    Entertainment getRandomEntertaiment();

}
