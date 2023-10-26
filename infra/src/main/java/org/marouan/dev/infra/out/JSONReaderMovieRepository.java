package org.marouan.dev.infra.out;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.marouan.dev.core.domain.Movie;
import org.marouan.dev.core.repository.MovieRepository;

@RequiredArgsConstructor
public class JSONReaderMovieRepository implements MovieRepository {
  private final ObjectMapper objectMapper;
  private final FileReader fileReader;
  public static final String MOVIES_FILE_PATH = "movies.json";

  @Override
  public List<Movie> getMoviesOrder() {
    try {
      return objectMapper.readValue(fileReader.getFileAsIOStream(MOVIES_FILE_PATH), new TypeReference<>() {});
    } catch (IOException e) {
      throw new RuntimeException("An IOException occurred while reading the file", e);
    }
  }
}
