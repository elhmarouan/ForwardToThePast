package org.marouan.dev.core.usecase;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.marouan.dev.core.domain.Cart;
import org.marouan.dev.core.domain.Discount;
import org.marouan.dev.core.domain.DiscountedCart;
import org.marouan.dev.core.domain.Movie;
import org.marouan.dev.core.repository.DiscountRepository;
import org.marouan.dev.core.repository.MovieRepository;

@RequiredArgsConstructor
public class CheckoutMovieOrderUseCase {

  private final DiscountRepository discountRepository;
  private final MovieRepository movieRepository;

  public BigDecimal process() {
    final List<Discount> currentDiscounts = discountRepository.getAvailableDiscounts();
    final List<Movie> movies = movieRepository.getMoviesOrder();

    final Cart cart = new Cart(movies);
    final DiscountedCart discountedCart = new DiscountedCart(cart, currentDiscounts);
    return discountedCart.calculatePrice();
  }
}
