package org.marouan.dev.core.domain;

import java.math.BigDecimal;
import java.util.List;

public record Cart(List<Movie> movies) {
  public void addMovie(final Movie movie) {
    movies.add(movie);
  }

  public BigDecimal calculateDiscountedPrice(final Discount discount) {
    return movies.stream()
        .map(movie -> movie.calculateDiscountedPrice(discount))
        .reduce(BigDecimal::add)
        .orElse(BigDecimal.ZERO);
  }

  public BigDecimal calculatePrice() {
    return movies.stream()
        .map(Movie::price)
        .reduce(BigDecimal::add)
        .orElse(BigDecimal.ZERO);
  }

  public Integer getNumberOfDifferentMovieParts(final String movieTitle) {
    return Math.toIntExact(movies.stream()
        .filter(movie -> movieTitle.equals(movie.title()))
        .map(Movie::part)
        .distinct().count());
  }
}
