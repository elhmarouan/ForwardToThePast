package org.marouan.dev.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.marouan.dev.core.domain.Cart;
import org.marouan.dev.core.domain.Discount;
import org.marouan.dev.core.domain.DiscountedCart;
import org.marouan.dev.core.domain.Movie;

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

  @ParameterizedTest
  @MethodSource("provideMoviesUseCases")
  void calculatePrice_shouldReturnCartDiscountedPrice_whenValidCartForDifferentUseCases(final List<Movie> movies, final BigDecimal price) {
    // GIVEN
    final Cart cart = new Cart(movies);
    final DiscountedCart discountedCart = new DiscountedCart(cart, discounts);

    // WHEN
    final BigDecimal calculatedPrice = discountedCart.calculatePrice();

    // THEN
    assertEquals(0, calculatedPrice.compareTo(price));
  }


  private static Stream<Arguments> provideMoviesUseCases() {
    final Movie bTTF1 = new Movie("Back to the Future", 1, new BigDecimal(15));
    final Movie bTTF2 = new Movie("Back to the Future", 2, new BigDecimal(15));
    final Movie bTTF3 = new Movie("Back to the Future", 3, new BigDecimal(15));
    final Movie laChevre = new Movie("La chèvre", 1, new BigDecimal(20));

    return Stream.of(
        Arguments.of(List.of(bTTF1, bTTF2, bTTF3), new BigDecimal(36)),
        Arguments.of(List.of(bTTF1, bTTF3), new BigDecimal(27)),
        Arguments.of(List.of(bTTF1), new BigDecimal(15)),
        Arguments.of(List.of(bTTF1, bTTF2, bTTF3, bTTF2), new BigDecimal(48)),
        Arguments.of(List.of(bTTF1, bTTF2, bTTF3, laChevre), new BigDecimal(56))
    );
  }
}