package org.marouan.dev.core.repository;

import java.util.List;
import org.marouan.dev.core.domain.Movie;

public interface MovieRepository {
  List<Movie> getMoviesOrder();
}
