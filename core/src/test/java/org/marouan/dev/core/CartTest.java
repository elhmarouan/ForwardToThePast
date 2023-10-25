package org.marouan.dev.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class CartTest {

  private final static List<Discount> discounts = List.of(
      new Discount("Back to the Future", 2, BigDecimal.valueOf(0.1)),
      new Discount("Back to the Future", 3, BigDecimal.valueOf(0.2))
  );

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
    assertEquals(0, price.compareTo(new BigDecimal(55)));
  }

  @Test
  void calculatePrice_shouldReturnCartTotalPriceAndApplyDiscount_whenTwoBTTFPartsBought() {
    // GIVEN
    final List<Movie> movies = List.of(
        new Movie("Back to the Future", 1, new BigDecimal(15)),
        new Movie("Back to the Future", 2, new BigDecimal(15)),
        new Movie("Interstellar", 1, new BigDecimal(20))
    );
    final Cart cart = new Cart(movies);
    final DiscountedCart discountedCart = new DiscountedCart(cart, discounts);

    // WHEN
    final BigDecimal price = discountedCart.calculatePrice();

    // THEN
    assertEquals(0, price.compareTo(new BigDecimal(47)));
  }

  @Test
  void calculatePrice_shouldReturnCartTotalPriceAndNotApplyDiscount_whenTwoBTTFSamePartsBought() {
    // GIVEN
    final List<Movie> movies = List.of(
        new Movie("Back to the Future", 1, new BigDecimal(15)),
        new Movie("Back to the Future", 1, new BigDecimal(15)),
        new Movie("Interstellar", 1, new BigDecimal(20))
    );
    final Cart cart = new Cart(movies);
    final DiscountedCart discountedCart = new DiscountedCart(cart, discounts);

    // WHEN
    final BigDecimal price = discountedCart.calculatePrice();

    // THEN
    assertEquals(0, price.compareTo(new BigDecimal(50)));
  }
}