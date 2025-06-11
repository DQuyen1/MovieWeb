package com.example.movie.config;

import com.example.movie.entity.Video;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class VideoSpecification {

    public static Specification<Video> hasName(String name) {
        return (root, query, cb) ->
                (name == null || name.trim().isEmpty()) ? null
                        : cb.like(cb.lower(root.get("videoName")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Video> hasYear(String year) {
        return (root, query, cb) ->
                (year == null || year.trim().isEmpty()) ? null
                        : cb.equal(root.get("year"), year);
    }

    public static Specification<Video> hasCountry(String country) {
        return (root, query, cb) -> {
            if (country == null || country.trim().isEmpty()) return null;
            Join<Object, Object> countryJoin = root.join("countries", JoinType.LEFT);
            return cb.like(cb.lower(countryJoin.get("country_name")), "%" + country.toLowerCase() + "%");
        };
    }

    public static Specification<Video> hasGenre(String genre) {
        return (root, query, cb) -> {
            if (genre == null || genre.trim().isEmpty()) return null;
            Join<Object, Object> genreJoin = root.join("genres", JoinType.LEFT);
            return cb.like(cb.lower(genreJoin.get("name")), "%" + genre.toLowerCase() + "%");
        };
    }

}
