package org.marouan.dev.core.domain;

import java.math.BigDecimal;

public record Movie(String title, Integer part, BigDecimal price) {
  public BigDecimal calculateDiscountedPrice(final Discount discount) {
    if (!discount.movieTitle().equals(title)) {
      return price;
    }
    return price.subtract(price.multiply(discount.discountRate()));
  }
}
