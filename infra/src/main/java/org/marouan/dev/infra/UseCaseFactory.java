package org.marouan.dev.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.marouan.dev.core.repository.DiscountRepository;
import org.marouan.dev.core.repository.MovieRepository;
import org.marouan.dev.core.usecase.CheckoutMovieOrderUseCase;
import org.marouan.dev.infra.out.FileReader;
import org.marouan.dev.infra.out.JSONReaderMovieRepository;
import org.marouan.dev.infra.out.MockDiscountRepository;

public class UseCaseFactory {

  public CheckoutMovieOrderUseCase createCheckoutMovieOrderUseCase() {
    final DiscountRepository discountRepository = new MockDiscountRepository();
    final MovieRepository movieRepository = createMovieRepository();

    return new CheckoutMovieOrderUseCase(discountRepository, movieRepository);
  }

  private MovieRepository createMovieRepository() {
    final FileReader fileReader = new FileReader();
    final ObjectMapper objectMapper = new ObjectMapper();
    return new JSONReaderMovieRepository(objectMapper, fileReader);
  }
}
