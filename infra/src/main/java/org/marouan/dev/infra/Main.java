package org.marouan.dev.infra;

import java.math.BigDecimal;
import org.marouan.dev.core.usecase.CheckoutMovieOrderUseCase;

public class Main {

  public static void main(String[] args) {
    final UseCaseFactory useCaseFactory = new UseCaseFactory();
    final CheckoutMovieOrderUseCase checkoutMovieOrderUseCase = useCaseFactory.createCheckoutMovieOrderUseCase();

    final BigDecimal cartPrice = checkoutMovieOrderUseCase.process();

    System.out.printf("The price of your cart is: %s", cartPrice);
  }
}
