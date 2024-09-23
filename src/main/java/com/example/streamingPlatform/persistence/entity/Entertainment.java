package com.example.streamingPlatform.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entertainment")
public class Entertainment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 80)
    private String name;
    @Column(nullable = false, length = 100)
    private String gender;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeEntertaiment type;
    @Column(nullable = false, name = "number_views")
    private Integer numberViews;
    @Column(nullable = false)
    private Double score;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;


    @OneToMany(mappedBy = "entertainment")
    private Set<RatingEntertainment> ratingEntertainment = new HashSet<>();
}
