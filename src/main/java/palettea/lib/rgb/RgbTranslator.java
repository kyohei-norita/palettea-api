package palettea.lib.rgb;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import lombok.NonNull;
import palettea.lib.rgb.Rgb.ValidatedRgbValue;

import static io.vavr.control.Validation.invalid;
import static io.vavr.control.Validation.valid;

public class RgbTranslator {
  public Validation<Seq<ValidationError>, Rgb> translate(@NonNull final RgbJson rgbJson) {
    return Validation
      .combine(
        isValidRgbValue(rgbJson.r()) ? valid(new ValidatedRgbValue(rgbJson.r())) : invalid(ValidationError.r(rgbJson.r())),
        isValidRgbValue(rgbJson.g()) ? valid(new ValidatedRgbValue(rgbJson.g())) : invalid(ValidationError.g(rgbJson.g())),
        isValidRgbValue(rgbJson.b()) ? valid(new ValidatedRgbValue(rgbJson.b())) : invalid(ValidationError.b(rgbJson.b()))
      )
      .ap(Rgb::new);
  }

  private static boolean isValidRgbValue(int rgbValue) {
    return (0 <= rgbValue && rgbValue <= 255);
  }

  public sealed interface ValidationError {
    record R(int r) implements ValidationError {}
    record G(int g) implements ValidationError {}
    record B(int b) implements ValidationError {}
    static ValidationError r(int r) {return new R(r);}
    static ValidationError g(int g) {return new G(g);}
    static ValidationError b(int b) {return new B(b);}
  }
}
