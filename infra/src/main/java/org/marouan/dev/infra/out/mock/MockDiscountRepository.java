package org.marouan.dev.infra.out.mock;

import java.util.List;
import org.marouan.dev.core.domain.Discount;
import org.marouan.dev.core.repository.DiscountRepository;

public class MockDiscountRepository implements DiscountRepository {

  @Override
  public List<Discount> getAvailableDiscounts() {
    return DiscountProvider.CURRENT_DISCOUNTS;
  }
}
