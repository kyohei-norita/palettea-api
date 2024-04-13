package palettea.api.application.command.paint;

import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import palettea.api.domain.manufacturer.ManufacturerId;
import palettea.api.domain.paint.*;
import palettea.lib.id.IdFactory;
import palettea.lib.id.IdValidator;
import palettea.lib.rgb.RgbJson;
import palettea.lib.rgb.RgbTranslator;

import java.util.UUID;

import static io.vavr.control.Validation.valid;
import static palettea.api.application.command.paint.PaintRegisterService.Error.referentialConstraintError;
import static palettea.api.application.command.paint.PaintRegisterService.Error.validationError;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaintRegisterService {
  @NonNull IdFactory<PaintId> paintIdFactory;
  @NonNull IdValidator<ManufacturerId> manufacturerIdValidator;
  @NonNull RgbTranslator rgbTranslator;
  @NonNull PaintRepository repository;

  public Validation<Seq<Error>, UUID> register(@NonNull final Input input) {
    val paintId = paintIdFactory.generate();
    val paintName = PaintName.create(input.name());
    val coatingType = switch (input.coatingType()) {
      case LACQUER -> CoatingType.LACQUER;
      case ACRYLIC -> CoatingType.ACRYLIC;
      case ENAMEL -> CoatingType.ENAMEL;
    };
    return Validation
      .combine(
        valid(paintId),
        manufacturerIdValidator
          .validate(input.manufacturerId)
          .mapError(v -> List.of(validationError(input.manufacturerId))),
        valid(paintName),
        rgbTranslator
          .translate(input.rgb)
          .mapError(e -> e.map(v -> switch (v) {
            case RgbTranslator.ValidationError.R r -> validationError(String.valueOf(r.r()));
            case RgbTranslator.ValidationError.G g -> validationError(String.valueOf(g.g()));
            case RgbTranslator.ValidationError.B b -> validationError(String.valueOf(b.b()));
          })),
        valid(coatingType)
      )
      .ap(Paint::create)
      .mapError(seq -> seq.flatMap(Function1.identity()))
      .flatMap(p -> repository.save(p).mapError(e -> List.of(referentialConstraintError())))
      .map(p -> p.id().value());
  }

  public record Input(
    @NonNull String name,
    @NonNull String manufacturerId,
    @NonNull RgbJson rgb,
    @NonNull CoatingType coatingType
  ) {
    public enum CoatingType {
      LACQUER,
      ACRYLIC,
      ENAMEL
    }
  }

  public sealed interface Error {
    record Validation(@NonNull String value) implements Error {}
    record ReferentialConstraint() implements Error {}
    static Error validationError(@NonNull String value) {return new Validation(value);}
    static Error referentialConstraintError() {return new ReferentialConstraint();}
  }
}
