package palettea.api.infrastructure;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import palettea.api.domain.manufacturer.ManufacturerId;
import palettea.api.domain.paint.PaintId;
import palettea.lib.id.IdFactory;

import java.util.UUID;
import java.util.function.Function;

@Configuration
public class IdFactoryConfiguration {
  private static <T> IdFactory<T> uuidFactory(final @NonNull Function<UUID, T> f) {
    return () -> f.apply(UUID.randomUUID());
  }

  @Bean IdFactory<PaintId> paintIdFactory() {return uuidFactory(PaintId::reconstruct);}
  @Bean IdFactory<ManufacturerId> manufacturerIdFactory() {return uuidFactory(ManufacturerId::reconstruct);}
}
