package palettea.lib.id;

import io.vavr.control.Validation;

public interface IdValidator<T> {

  record ValidationError() {}

  Validation<ValidationError, T> validate(String id);
}
