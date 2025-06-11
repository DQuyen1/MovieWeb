package com.example.movie.repository;

import com.example.movie.entity.Video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer>, JpaSpecificationExecutor<Video> {
    @Query("SELECT v FROM Video v WHERE LOWER(v.videoName) LIKE LOWER(CONCAT('%', :videoName, '%'))")
    Video findByVideoName(String videoName);


    @Query("SELECT v FROM Video v WHERE LOWER(v.genres) LIKE LOWER(CONCAT('%', :genres, '%'))")
    Video findByVideoGenre(String genre);


//    @Query("""
//    SELECT DISTINCT v FROM Video v
//    LEFT JOIN v.countries c
//    WHERE (:videoName IS NULL OR LOWER(v.videoName) LIKE LOWER(CONCAT('%', :videoName, '%')))
//      AND (:year IS NULL OR v.year = :year)
//      AND (:country IS NULL OR LOWER(c.country_name) LIKE LOWER(CONCAT('%', :country, '%')))
//""")
//@Query("""
//SELECT DISTINCT v FROM Video v
//LEFT JOIN v.countries c
//WHERE (:videoName IS NULL OR v.videoName LIKE CONCAT('%', :videoName, '%'))
//  AND (:year IS NULL OR v.year = :year)
//  AND (:country IS NULL OR c.country_name LIKE CONCAT('%', :country, '%'))
//""")
//
//
//List<Video> searchVideo(
//            @Param("videoName") String videoName,
//            @Param("year") String year,
//            @Param("country") String country
//    );

}
