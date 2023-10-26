package org.marouan.dev.infra.out;

import java.util.List;
import org.marouan.dev.core.domain.Discount;
import org.marouan.dev.core.repository.DiscountRepository;
import org.marouan.dev.infra.out.mock.DiscountProvider;

public class MockDiscountRepository implements DiscountRepository {

  @Override
  public List<Discount> getAvailableDiscounts() {
    return DiscountProvider.CURRENT_DISCOUNTS;
  }
}
