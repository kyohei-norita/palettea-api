package palettea.api.infrastructure;

import io.vavr.control.Validation;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import palettea.api.domain.manufacturer.ManufacturerId;
import palettea.api.domain.paint.PaintId;
import palettea.lib.id.IdValidator;

import java.util.UUID;
import java.util.function.Function;

@Configuration
public class IdValidatorConfiguration {
  private <T> IdValidator<T> idValidator(@NonNull final Function<UUID, T> f) {
    return (id) -> {
      try {
        return Validation.valid(f.apply(UUID.fromString(id)));
      } catch (IllegalArgumentException e) {
        return Validation.invalid(new IdValidator.ValidationError());
      }
    };
  }

  @Bean IdValidator<PaintId> paintIdTranslator() {return idValidator(PaintId::reconstruct);}
  @Bean IdValidator<ManufacturerId> manufacturerIdTranslator() {return idValidator(ManufacturerId::reconstruct);}
}
