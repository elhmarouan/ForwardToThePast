package org.marouan.dev.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.marouan.dev.core.domain.Discount;
import org.marouan.dev.core.domain.Movie;
import org.marouan.dev.core.repository.DiscountRepository;
import org.marouan.dev.core.repository.MovieRepository;

class CheckoutMovieOrderUseCaseTest {

  private static final List<Discount> discounts = List.of(
      new Discount("Back to the Future", 2, BigDecimal.valueOf(0.1)),
      new Discount("Back to the Future", 3, BigDecimal.valueOf(0.2))
  );

  private static final List<Movie> movies = List.of(
      new Movie("Back to the Future", 1, new BigDecimal(15)),
      new Movie("Back to the Future", 2, new BigDecimal(15)),
      new Movie("Back to the Future", 3, new BigDecimal(15)),
      new Movie("Back to the Future", 2, new BigDecimal(15))
  );

  @Test
  void process_shouldReturnCartDiscountedPrice_whenRequested() {
    // GIVEN
    final DiscountRepository discountRepository = mock(DiscountRepository.class);
    final MovieRepository movieRepository = mock(MovieRepository.class);
    final CheckoutMovieOrderUseCase checkoutMovieOrderUseCase = new CheckoutMovieOrderUseCase(discountRepository, movieRepository);

    doReturn(movies).when(movieRepository).getMoviesOrder();
    doReturn(discounts).when(discountRepository).getAvailableDiscounts();

    // WHEN
    final BigDecimal price = checkoutMovieOrderUseCase.process();

    // THEN
    assertEquals(0, price.compareTo(new BigDecimal(48)));
  }
}