package com.example.movie.serviceImpl;



import com.example.movie.config.VideoSpecification;
import com.example.movie.dto.VideoDTO;
import com.example.movie.entity.Company;
import com.example.movie.entity.Country;
import com.example.movie.entity.Genre;
import com.example.movie.entity.Video;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.helper.FileUpload;
import com.example.movie.mapper.VideoMapper;
import com.example.movie.payload.response.CloudinaryResponse;
import com.example.movie.repository.*;
import com.example.movie.service.VideoService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

  final private VideoMapper videoMapper;
  final private VideoRepository repo;
  final private CloudinaryServiceImpl cloudinaryService;
  final private CountryRepository countryRepository;
  final private GenreRepository genreRepository;
  final private CompanyRepository companyRepository;
  final private LanguageRepository languageRepository;

  @Value("${files.video}")
  String DIR;

  @PostConstruct
  public void init() {
    File file = new File(DIR);

    if(!file.exists()) {
        file.mkdir();
        System.out.println("Folder created");
    } else {
      System.out.println("Folder already created before");
    }
  }

  @Autowired
  public VideoServiceImpl(VideoRepository repo, VideoMapper videoMapper, CloudinaryServiceImpl cloudinaryService, CountryRepository countryRepository, GenreRepository genreRepository, CompanyRepository companyRepository, LanguageRepository languageRepository) {
    this.repo = repo;
    this.videoMapper = videoMapper;
    this.cloudinaryService = cloudinaryService;
    this.countryRepository = countryRepository;
    this.genreRepository = genreRepository;
    this.companyRepository = companyRepository;
    this.languageRepository = languageRepository;
  }

  @Override
  public List<VideoDTO> getAll() {
    List<Video> videos = repo.findAll();
    return videos.stream().map(videoMapper::convertToDTO).collect(Collectors.toList());

  }


  @Override
  public VideoDTO findVideoById(int id) {
    Video video = repo.findById(id).orElseThrow(() -> {
      return new ResourceNotFoundException("Video not found");
    });;

    return videoMapper.convertToDTO(video);
  }

  @Override
  public VideoDTO findVideoByName(String videoName) {
      Video requestedVideo = repo.findByVideoName(videoName);
    if (requestedVideo == null) {
      throw new ResourceNotFoundException("Video not found with name: " + videoName);
    }
    return videoMapper.convertToDTO(requestedVideo);
  }

  @Override
  public VideoDTO findVideoByGenre(String genre) {
    Video requestedVideo = repo.findByVideoGenre(genre);
    if (requestedVideo == null) {
      throw new ResourceNotFoundException("Video not found with genre: " + genre);
    }
    return videoMapper.convertToDTO(requestedVideo);
  }

  @Override
  public List<Video> searchVideos(String videoName, String year, String country, String genre) {
    Specification<Video> spec = Specification
            .where(VideoSpecification.hasName(videoName))
            .and(VideoSpecification.hasYear(year))
            .and(VideoSpecification.hasCountry(country))
            .and(VideoSpecification.hasGenre(genre));
    return repo.findAll(spec);
  }


  @Transactional
  public CloudinaryResponse uploadImage(final MultipartFile file) {
    FileUpload.assertAllowed(file, FileUpload.IMAGE_PATTERN);
    final String fileName = FileUpload.getFileName(file.getOriginalFilename());
    final CloudinaryResponse response = this.cloudinaryService.uploadFile(file, fileName);
    return response;
  }





  @Override
  public VideoDTO createVideoWithFile(Video newVideo, MultipartFile file) {
    try {
      String filename = file.getOriginalFilename();
      String contentType = file.getContentType();
      InputStream inputStream = file.getInputStream();

      //file path
      String cleanFileName = StringUtils.cleanPath(filename);

      //folder path: create
      String cleanFolder = StringUtils.cleanPath(DIR);

      //folder path with filename
      Path path = Paths.get(cleanFolder, cleanFileName);

      //Copy file to folder
      Files.copy(inputStream,path, StandardCopyOption.REPLACE_EXISTING);

      System.out.println(path);
      System.out.println("type: " + contentType);

      newVideo.setFilePath(path.toString());
      newVideo.setContentType(contentType);


      Video video  = repo.save(newVideo);
      return videoMapper.convertToDTO(video);

    } catch (IOException e) {
      e.printStackTrace();
    }

    return  null;
  }

  @Override
  public VideoDTO createVideo(Video newVideo) {

    if (newVideo.getLanguage() == null) {
      newVideo.setLanguage(languageRepository.findById(3).orElse(null));
    }

    if (newVideo.getCountries() == null || newVideo.getCountries().isEmpty()) {
      Set<Country> defaultCountries = new HashSet<>();
      countryRepository.findById(3).ifPresent(defaultCountries::add);
      newVideo.setCountries(defaultCountries);
    }

    if (newVideo.getCompanies() == null || newVideo.getCompanies().isEmpty()) {
      Set<Company> defaultCompanies = new HashSet<>();
      companyRepository.findById(57).ifPresent(defaultCompanies::add);
      newVideo.setCompanies(defaultCompanies);
    }

    if (newVideo.getGenres() == null || newVideo.getGenres().isEmpty()) {
      Set<Genre> defaultGenres = new HashSet<>();
      List<Integer> genreIds = List.of(2, 105, 107, 115);
      for (int id : genreIds) {
        genreRepository.findById(id).ifPresent(defaultGenres::add);
      }
      newVideo.setGenres(defaultGenres);
    }

    // likedByUsers and seasons are handled by listener or here
    if (newVideo.getLikedByUsers() == null) {
      newVideo.setLikedByUsers(new HashSet<>());
    }
    if (newVideo.getSeasons() == null) {
      newVideo.setSeasons(new ArrayList<>());
    }

    Video video  = repo.save(newVideo);
    return videoMapper.convertToDTO(video);
  }


  @Override
  public VideoDTO updateVideo(int videoId, VideoDTO videoDTO) {
    Video video = repo.findById(videoId).orElseThrow(() -> {
      return new ResourceNotFoundException("This Video is not exist");
    });

    video.setVideoName(videoDTO.getVideoName());
    video.setDescription(videoDTO.getDescription());
    video.setYear(videoDTO.getYear());
    video.setRating(videoDTO.getRating());
    video.setPost_url(videoDTO.getPost_url());
    video.setLength(videoDTO.getLength());
    video.setStatus(videoDTO.getStatus());
    video.setUpdate_at(videoDTO.getUpdate_at());
    video.setCreate_at(videoDTO.getCreate_at());
    video.setIs_deleted(videoDTO.isIs_deleted());

    Video updatedVideo = repo.save(video);

    return videoMapper.convertToDTO(updatedVideo);
  }

  @Override
  public boolean deleteVideo(int id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

}
