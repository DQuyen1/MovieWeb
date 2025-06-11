package com.example.movie.controller;


import com.example.movie.config.AppConstants;
import com.example.movie.dto.VideoDTO;
import com.example.movie.entity.Video;
import com.example.movie.entity.ResponseMessage;
import com.example.movie.helper.LimitedInputStream;
import com.example.movie.payload.request.VideoRequest;
import com.example.movie.payload.response.CloudinaryResponse;
import com.example.movie.serviceImpl.VideoServiceImpl;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/videos")
public class VideoController {

    VideoServiceImpl videoService;

    //if only has 1 constructor this annotation autowired is optional
    @Autowired
    public VideoController(VideoServiceImpl videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadImage(@RequestPart("file") MultipartFile file) {
       CloudinaryResponse result = videoService.uploadImage(file);
       return new ResponseEntity<>(new ResponseMessage("created new image successfully", result), HttpStatus.CREATED);
    }



//    @GetMapping("/stream/{movieId}")
//    public ResponseEntity<Resource> streamMovie(@PathVariable int movieId) {
//
//        VideoDTO movie = videoService.findVideoById(movieId);
//
//        String contentType = movie.getContentType();
//        String filePath = movie.getFilePath();
//
//        Resource resource = new FileSystemResource(filePath);
//
//        if (contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
//    }


//    @GetMapping("/stream/range/{movieId}")
//    public ResponseEntity<Resource> streamMovieInRange(@PathVariable int movieId, @RequestHeader(value = "Range", required = false) String range) {
//        System.out.println("Range: " + range);
//
//        VideoDTO movie = videoService.findVideoById(movieId);
//        Path path = Paths.get(movie.getFilePath());
//
//        Resource resource = new FileSystemResource(path);
//
//        String contentType = movie.getContentType();
//
//        if (contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        //file ki length
//        long fileLength = path.toFile().length();
//
//        if (range == null) {
//            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
//        }
//
//        long rangeStart;
//
//        long rangeEnd;
//
//
//        String[] ranges = range.replace("bytes=", "").split("-");
//        rangeStart = Long.parseLong(ranges[0]);
//
//        rangeEnd = rangeStart + AppConstants.CHUNK_SIZE - 1;
//
//        if (rangeEnd >= fileLength) {
//            rangeEnd = fileLength - 1;
//        }
//
//        System.out.println("Range start: " + rangeStart);
//        System.out.println("Range end: " + rangeEnd);
//        InputStream inputStream;
//
//        try {
//            inputStream = Files.newInputStream(path);
//            inputStream.skip(rangeStart);
//            long contentLength = rangeEnd - rangeStart + 1;
//
//
//            byte[] data = new byte[(int) contentLength];
//            int read = inputStream.read(data, 0, data.length);
//            System.out.println("Read(number of bytes): " + read);
//
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + fileLength);
//            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//            headers.add("Pragma", "no-cache");
//            headers.add("Expires", "0");
//            headers.add("X-Content-Type-Options", "nosniff");
//            headers.setContentLength(contentLength);
//
//
//            return ResponseEntity
//                    .status(HttpStatus.PARTIAL_CONTENT)
//                    .headers(headers)
//                    .contentType(MediaType.parseMediaType(contentType))
//                    .body(new ByteArrayResource(data));
//
//        } catch (IOException ex) {
//
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }


//    @GetMapping("/stream-alt/{movieId}")
//    public ResponseEntity<Resource> streamVideoByRange(
//            @PathVariable int movieId,
//            @RequestHeader(value = "Range", required = false) String rangeHeader) {
//
//        try {
//            VideoDTO movie = videoService.findVideoById(movieId);
//            Path videoPath = Paths.get(movie.getFilePath());
//
//            if (!Files.exists(videoPath)) {
//                return ResponseEntity.notFound().build();
//            }
//
//            long fileSize = Files.size(videoPath);
//            String contentType = movie.getContentType();
//            if (contentType == null) {
//                contentType = "application/octet-stream";
//            }
//
//            long rangeStart = 0;
//            long rangeEnd = fileSize - 1;
//
//            if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
//                String[] parts = rangeHeader.replace("bytes=", "").split("-");
//                rangeStart = Long.parseLong(parts[0]);
//                if (parts.length > 1 && !parts[1].isEmpty()) {
//                    rangeEnd = Long.parseLong(parts[1]);
//                } else {
//                    // Limit chunk size to 1MB if end not provided
//                    long chunkSize = 1024 * 1024;
//                    rangeEnd = Math.min(rangeStart + chunkSize - 1, fileSize - 1);
//                }
//            }
//
//            if (rangeStart > rangeEnd || rangeEnd >= fileSize) {
//                return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
//                        .header("Content-Range", "bytes */" + fileSize)
//                        .build();
//            }
//
//            long contentLength = rangeEnd - rangeStart + 1;
//            InputStream inputStream = Files.newInputStream(videoPath);
//            inputStream.skip(rangeStart);
//
//            InputStreamResource resource = new InputStreamResource(new LimitedInputStream(inputStream, contentLength));
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + fileSize);
//            headers.add("Accept-Ranges", "bytes");
//            headers.setContentLength(contentLength);
//            headers.setCacheControl("no-cache");
//
//            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
//                    .headers(headers)
//                    .contentType(MediaType.parseMediaType(contentType))
//                    .body(resource);
//
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }


    @GetMapping
    public ResponseEntity<ResponseMessage> getAllVideo() {
        return new ResponseEntity<>(new ResponseMessage("Videos retrieved successfully", videoService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getVideo(@PathVariable int id) {
        VideoDTO videoDTO = videoService.findVideoById(id);
        return new ResponseEntity<>(new ResponseMessage("Video retrieved successfully", videoDTO), HttpStatus.OK);
    }


//    @PostMapping("/search")
//    public ResponseEntity<ResponseMessage> getVideoWithName(@RequestBody VideoRequest videoName) {
//        VideoDTO videoDTO = videoService.findVideoByName(videoName.getVideoName().trim());
//        return new ResponseEntity<>(new ResponseMessage("Video retrieved successfully", videoDTO), HttpStatus.OK);
//    }


        @GetMapping("/search")
        public ResponseEntity<ResponseMessage> searchVideos(
                @RequestParam(required = false) String videoName,
                @RequestParam(required = false) String year,
                @RequestParam(required = false) String country,
                @RequestParam(required = false) String genre) {

            List<Video> videos = videoService.searchVideos(videoName, year, country, genre);
            return ResponseEntity.ok(new ResponseMessage("Videos retrieved successfully", videos));
        }





    @PostMapping
    public ResponseEntity<ResponseMessage> createMovieWithFile(@Valid @RequestPart("movie") Video video, @RequestPart("file") MultipartFile file) {
        return new ResponseEntity<>(new ResponseMessage("created new video successfully", videoService.createVideoWithFile(video, file)), HttpStatus.CREATED);

    }


    @PostMapping("/createVideo")
    public ResponseEntity<ResponseMessage> createMovie(@Valid @RequestBody Video video) {
        return new ResponseEntity<>(new ResponseMessage("created new video successfully", videoService.createVideo(video)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateVideo(@Valid @RequestBody VideoDTO videoDTO, @PathVariable int id) {

        VideoDTO updatedMovie = videoService.updateVideo(id, videoDTO);

        return new ResponseEntity<>(new ResponseMessage("Update video successfully", updatedMovie), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteVideo(@PathVariable int id) {

        boolean isDeleted = videoService.deleteVideo(id);

        if (isDeleted) {
            return new ResponseEntity<>(
                    new ResponseMessage("Video with id #" + id
                            + " deleted successfully", null),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new ResponseMessage("Video not found", null),
                    HttpStatus.NOT_FOUND
            );
        }
    }


}
