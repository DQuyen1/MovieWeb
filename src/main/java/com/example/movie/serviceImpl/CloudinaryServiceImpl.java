package com.example.movie.serviceImpl;

import com.cloudinary.Cloudinary;
import com.example.movie.exception.FuncErrorException;
import com.example.movie.payload.response.CloudinaryResponse;
import com.example.movie.service.CloudinaryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {


    final private Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    @Override
    @Transactional
    public CloudinaryResponse uploadFile(MultipartFile file, String fileName) {
        try {
            final Map result   = this.cloudinary.uploader()
                    .upload(file.getBytes(),
                            Map.of("public_id",
                                    "movieapp/"
                                            + fileName));
            final String url      = (String) result.get("secure_url");
            final String publicId = (String) result.get("public_id");
            return new CloudinaryResponse(publicId, url);
        } catch (final Exception e) {
            e.printStackTrace(); // Or use a logger
            throw new FuncErrorException("Failed to upload file: " + e.getMessage());
        }

    }
}
