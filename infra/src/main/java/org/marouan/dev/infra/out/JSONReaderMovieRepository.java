package org.marouan.dev.infra.out;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.marouan.dev.core.domain.Movie;
import org.marouan.dev.core.repository.MovieRepository;

public class JSONReaderMovieRepository implements MovieRepository {
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final FileReader fileReader = new FileReader();
  public static final String MOVIES_FILE_PATH = "movies.json";

  @Override
  public List<Movie> getUserMovies() {
    try {
      return objectMapper.readValue(fileReader.getFileAsIOStream(MOVIES_FILE_PATH), new TypeReference<>() {});
    } catch (IOException e) {
      throw new RuntimeException("An IOException occurred while reading the file", e);
    }
  }
}
