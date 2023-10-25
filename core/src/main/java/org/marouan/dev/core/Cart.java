package org.marouan.dev.core;

import java.math.BigDecimal;
import java.util.List;

public record Cart(List<Movie> movies) {
  public void addMovie(final Movie movie) {
    movies.add(movie);
  }

  public BigDecimal calculatePrice() {
    return new BigDecimal(0);
  }
}
