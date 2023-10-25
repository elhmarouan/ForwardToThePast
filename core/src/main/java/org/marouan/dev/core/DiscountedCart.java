package org.marouan.dev.core;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public record DiscountedCart(Cart cart, List<Discount> discounts) {

  public BigDecimal calculatePrice() {
    return getMatchingDiscount().map(cart::calculateDiscountedPrice)
        .orElse(cart.calculatePrice());
  }

  private Optional<Discount> getMatchingDiscount() {
    return discounts.stream()
        .filter(this::isMatchingDiscount)
        .findFirst();
  }

  private boolean isMatchingDiscount(final Discount discount) {
    return discount.numberOfParts()
        .equals(cart.getNumberOfMovieParts(discount.movieTitle()));
  }
}
