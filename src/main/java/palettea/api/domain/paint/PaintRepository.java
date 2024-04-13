package palettea.api.domain.paint;

import io.vavr.control.Validation;

public interface PaintRepository {
  record ReferentialConstraintError() {}

  Validation<ReferentialConstraintError, Paint> save(Paint paint);
}
