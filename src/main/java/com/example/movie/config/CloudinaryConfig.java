package com.example.movie.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {


    @Bean
    public Cloudinary cloudinary() {
        final Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dchov8fes");
        config.put("api_key","243469555716977");
        config.put("api_secret", "ABUfPFFVX6rXSozk9DNS79qCWOE");
        return new Cloudinary(config);


    }



}
