package com.example.movie.service;

import com.example.movie.payload.response.CloudinaryResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    CloudinaryResponse uploadFile(MultipartFile file, String filename);

}
