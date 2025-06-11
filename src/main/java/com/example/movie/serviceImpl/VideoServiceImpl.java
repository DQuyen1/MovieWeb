package com.example.movie.serviceImpl;



import com.example.movie.dto.MovieDTO;
import com.example.movie.entity.Movie;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.mapper.MovieMapper;
import com.example.movie.repository.MovieRepository;
import com.example.movie.service.MovieService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

  final private MovieMapper movieMapper;
  final private MovieRepository repo;

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
  public MovieServiceImpl(MovieRepository repo, MovieMapper movieMapper) {
    this.repo = repo;
    this.movieMapper = movieMapper;
  }


  @Override
  public List<MovieDTO> getAll() {
    List<Movie> movies = repo.findAll();
    return movies.stream().map(movieMapper::convertToDTO).collect(Collectors.toList());

  }


  @Override
  public MovieDTO findMovieById(int id) {
    Movie movie = repo.findById(id).orElseThrow(() -> {
      return new ResourceNotFoundException("Movie not found");
    });;

    return movieMapper.convertToDTO(movie);

  }

  @Override
  public MovieDTO createMovie(Movie newMovie, MultipartFile file) {
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

      newMovie.setFilePath(path.toString());
      newMovie.setContentType(contentType);


      Movie movie  = repo.save(newMovie);
      return movieMapper.convertToDTO(movie);

    } catch (IOException e) {
      e.printStackTrace();
    }

    return  null;
  }


  @Override
  public MovieDTO updateMovie(int movieId, MovieDTO movieDTO) {
    Movie movie = repo.findById(movieId).orElseThrow(() -> {
      return new ResourceNotFoundException("This movie is not exist");
    });

    movie.setMovie_name(movieDTO.getMovie_name());
    movie.setDescription(movieDTO.getDescription());
    movie.setYear(movieDTO.getYear());
    movie.setRating(movieDTO.getRating());
    movie.setPost_url(movieDTO.getPost_url());
    movie.setLength(movieDTO.getLength());
    movie.setStatus(movieDTO.getStatus());
    movie.setUpdate_at(movieDTO.getUpdate_at());
    movie.setCreate_at(movieDTO.getCreate_at());
    movie.setIs_deleted(movieDTO.isIs_deleted());

    Movie updatedMovie = repo.save(movie);

    return movieMapper.convertToDTO(updatedMovie);
  }

  @Override
  public boolean deleteMovie(int id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

}
