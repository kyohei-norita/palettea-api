package palettea.api.infrastructure.domain.paint;

import io.vavr.control.Validation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import palettea.api.domain.paint.Paint;
import palettea.api.domain.paint.PaintRepository;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaintPgRepository implements PaintRepository {

  @NonNull PaintPgMapper paintPgMapper;

  @Override
  public Validation<ReferentialConstraintError, Paint> save(@NonNull final Paint paint) {
    paintPgMapper.insert(
      new PaintPgMapper.InsertInput(
        paint.id().value(),
        paint.paintName().value(),
        paint.rgb().toString(),
        paint.coatingType().name()
      )
    );
    return Validation.valid(paint);
  }
}
