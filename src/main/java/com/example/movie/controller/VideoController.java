package com.example.movie.controller;


import com.example.movie.config.AppConstants;
import com.example.movie.dto.VideoDTO;
import com.example.movie.entity.Movie;
import com.example.movie.entity.ResponseMessage;
import com.example.movie.helper.LimitedInputStream;
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



@RestController
@RequestMapping(path = "/api/v1/movies")
public class MovieController {

  VideoServiceImpl movieService;

  //if only has 1 constructor this annotation autowired is optional
  @Autowired
  public MovieController(VideoServiceImpl movieService) {
    this.movieService = movieService;
  }


  @GetMapping("/stream/{movieId}")
  public ResponseEntity<Resource> streamMovie(@PathVariable int movieId) {

    VideoDTO movie = movieService.findMovieById(movieId);

    String contentType = movie.getContentType();
    String filePath = movie.getFilePath();

    Resource resource =  new FileSystemResource(filePath);

    if(contentType == null) {
       contentType = "application/octet-stream";
    }

    return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
  }


  @GetMapping("/stream/range/{movieId}")
  public ResponseEntity<Resource> streamMovieInRange(@PathVariable int movieId, @RequestHeader(value = "Range", required = false) String range) {
    System.out.println("Range: " + range);

    VideoDTO movie = movieService.findMovieById(movieId);
    Path path = Paths.get(movie.getFilePath());

    Resource resource = new FileSystemResource(path);

    String contentType = movie.getContentType();

    if(contentType == null) {
      contentType = "application/octet-stream";
    }

    //file ki length
    long fileLength = path.toFile().length();

    if(range == null){
      return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
    }

    long rangeStart;

    long rangeEnd;



    String[] ranges = range.replace("bytes=", "").split("-");
    rangeStart = Long.parseLong(ranges[0]);

    rangeEnd = rangeStart + AppConstants.CHUNK_SIZE - 1;

    if(rangeEnd >= fileLength) {
      rangeEnd = fileLength - 1;
    }

    System.out.println("Range start: " + rangeStart);
    System.out.println("Range end: " + rangeEnd);
    InputStream inputStream;

      try {
        inputStream = Files.newInputStream(path);
        inputStream.skip(rangeStart);
        long contentLength = rangeEnd - rangeStart + 1;


        byte[] data = new byte[(int) contentLength];
        int read = inputStream.read(data,0, data.length);
        System.out.println("Read(number of bytes): " + read);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + fileLength);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("X-Content-Type-Options", "nosniff");
        headers.setContentLength(contentLength);


        return ResponseEntity 
                .status(HttpStatus.PARTIAL_CONTENT)
                .headers(headers)
                .contentType(MediaType.parseMediaType(contentType))
                .body(new ByteArrayResource(data));

      } catch (IOException ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
  }



  @GetMapping("/stream-alt/{movieId}")
  public ResponseEntity<Resource> streamVideoByRange(
          @PathVariable int movieId,
          @RequestHeader(value = "Range", required = false) String rangeHeader) {

    try {
      VideoDTO movie = movieService.findMovieById(movieId);
      Path videoPath = Paths.get(movie.getFilePath());

      if (!Files.exists(videoPath)) {
        return ResponseEntity.notFound().build();
      }

      long fileSize = Files.size(videoPath);
      String contentType = movie.getContentType();
      if (contentType == null) {
        contentType = "application/octet-stream";
      }

      long rangeStart = 0;
      long rangeEnd = fileSize - 1;

      if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
        String[] parts = rangeHeader.replace("bytes=", "").split("-");
        rangeStart = Long.parseLong(parts[0]);
        if (parts.length > 1 && !parts[1].isEmpty()) {
          rangeEnd = Long.parseLong(parts[1]);
        } else {
          // Limit chunk size to 1MB if end not provided
          long chunkSize = 1024 * 1024;
          rangeEnd = Math.min(rangeStart + chunkSize - 1, fileSize - 1);
        }
      }

      if (rangeStart > rangeEnd || rangeEnd >= fileSize) {
        return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
                .header("Content-Range", "bytes */" + fileSize)
                .build();
      }

      long contentLength = rangeEnd - rangeStart + 1;
      InputStream inputStream = Files.newInputStream(videoPath);
      inputStream.skip(rangeStart);

      InputStreamResource resource = new InputStreamResource(new LimitedInputStream(inputStream, contentLength));

      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + fileSize);
      headers.add("Accept-Ranges", "bytes");
      headers.setContentLength(contentLength);
      headers.setCacheControl("no-cache");

      return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
              .headers(headers)
              .contentType(MediaType.parseMediaType(contentType))
              .body(resource);

    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }



  @GetMapping
  public ResponseEntity<ResponseMessage> getAllMovie() {
    return new ResponseEntity<>(new ResponseMessage("Movies retrieved successfully", movieService.getAll()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseMessage> getMovie(@PathVariable int id) {
    VideoDTO videoDTO = movieService.findMovieById(id);
    return new ResponseEntity<>(new ResponseMessage("Movie retrieved successfully", videoDTO), HttpStatus.OK);
  }


  @PostMapping
  public ResponseEntity<ResponseMessage> createMovie(@Valid @RequestPart("movie") Movie movie, @RequestPart("file") MultipartFile file) {

    return new ResponseEntity<>(new ResponseMessage("created new movie successfully", movieService.createMovie(movie, file)), HttpStatus.CREATED);

  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseMessage> updateMovie(@Valid @RequestBody VideoDTO videoDTO, @PathVariable int id) {

    VideoDTO updatedMovie = movieService.updateMovie(id, videoDTO);

    return new ResponseEntity<>(new ResponseMessage("Update movie successfully", updatedMovie), HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessage> deleteMovie(@PathVariable int id) {

    boolean isDeleted = movieService.deleteMovie(id);

    if (isDeleted) {
      return new ResponseEntity<>(
        new ResponseMessage("Movie with id #" + id
          + " deleted successfully", null),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(
        new ResponseMessage("Movie not found", null),
        HttpStatus.NOT_FOUND
      );
    }
  }




}
