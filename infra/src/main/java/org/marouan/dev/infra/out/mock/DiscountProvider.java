package org.marouan.dev.infra.out.mock;

import java.math.BigDecimal;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.marouan.dev.core.domain.Discount;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscountProvider {
  public static final List<Discount> CURRENT_DISCOUNTS = List.of(
      new Discount("Back to the Future", 2, BigDecimal.valueOf(0.1)),
      new Discount("Back to the Future", 3, BigDecimal.valueOf(0.2))
  );
}
