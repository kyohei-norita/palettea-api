package palettea.api.infrastructure.domain.paint;

import io.vavr.control.Validation;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import palettea.api.domain.paint.Paint;
import palettea.api.domain.paint.PaintRepository;

@Repository
public class PaintPgRepository implements PaintRepository {
  @Override
  public Validation<ReferentialConstraintError, Paint> save(@NonNull final Paint paint) {
    return Validation.valid(paint);
  }
}
