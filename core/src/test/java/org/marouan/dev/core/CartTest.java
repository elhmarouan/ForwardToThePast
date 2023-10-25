package org.marouan.dev.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class CartTest {

  @Test
  void calculatePrice_shouldReturnCartTotalPrice_whenMoviesInCart() {
    // GIVEN
    final List<Movie> movies = List.of(
        new Movie("Back to the Future", 1, new BigDecimal(15)),
        new Movie("Le parrain", 1, new BigDecimal(20)),
        new Movie("Interstellar", 1, new BigDecimal(20))
    );
    final Cart cart = new Cart(movies);

    // WHEN
    final BigDecimal price = cart.calculatePrice();

    // THEN
    assertEquals(price, new BigDecimal(55));
  }
}