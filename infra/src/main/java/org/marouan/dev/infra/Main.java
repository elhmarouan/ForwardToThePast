package org.marouan.dev.infra;

import java.util.List;
import org.marouan.dev.core.domain.Cart;
import org.marouan.dev.core.domain.Discount;
import org.marouan.dev.core.domain.DiscountedCart;
import org.marouan.dev.core.domain.Movie;
import org.marouan.dev.core.repository.DiscountRepository;
import org.marouan.dev.infra.out.JSONReaderMovieRepository;
import org.marouan.dev.infra.out.mock.MockDiscountRepository;

public class Main {

  public static void main(String[] args) {
    final DiscountRepository discountRepository = new MockDiscountRepository();
    final JSONReaderMovieRepository jsonReaderMovieRepository = new JSONReaderMovieRepository();

    final List<Discount> currentDiscounts = discountRepository.getAvailableDiscounts();
    final List<Movie> movies = jsonReaderMovieRepository.getUserMovies();
    final Cart cart = new Cart(movies);
    final DiscountedCart discountedCart = new DiscountedCart(cart, currentDiscounts);

    System.out.printf("The price of your cart is: %s", discountedCart.calculatePrice());
  }
}
