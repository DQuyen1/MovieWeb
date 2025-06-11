package com.example.movie.service;

import com.example.movie.dto.VideoDTO;
import com.example.movie.entity.Video;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface VideoService {

  List<VideoDTO> getAll();

  VideoDTO findVideoById(int id);

  VideoDTO findVideoByName(String videoName);

  VideoDTO findVideoByGenre(String genre);

  List<Video> searchVideos(String videoName, String year, String country, String genre);

  VideoDTO createVideoWithFile(Video newVideo, MultipartFile file);

  VideoDTO createVideo(Video newVideo);

  VideoDTO updateVideo(int videoId, VideoDTO videoDTO);

  boolean deleteVideo(int id);

}
