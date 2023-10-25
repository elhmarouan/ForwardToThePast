package org.marouan.dev.core.domain;

import java.math.BigDecimal;

public record Discount(String movieTitle, Integer numberOfParts, BigDecimal discountRate) {

}
