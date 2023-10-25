package org.marouan.dev.core.repository;

import java.util.List;
import org.marouan.dev.core.domain.Discount;

public interface DiscountRepository {
  List<Discount> getAvailableDiscounts();
}
