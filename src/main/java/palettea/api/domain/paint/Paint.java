package palettea.api.domain.paint;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;
import palettea.api.domain.manufacturer.ManufacturerId;
import palettea.lib.rgb.Rgb;

@Value
@Accessors(fluent = true)
public class Paint {
  @NonNull PaintId id;
  @NonNull ManufacturerId manufacturerId;
  @NonNull PaintName paintName;
  @NonNull Rgb rgb;
  @NonNull CoatingType coatingType;

  public static Paint create(
    @NonNull final PaintId id,
    @NonNull final ManufacturerId manufacturerId,
    @NonNull final PaintName paintName,
    @NonNull final Rgb rgb,
    @NonNull final CoatingType coatingType
  ) {
    return new Paint(
      id,
      manufacturerId,
      paintName,
      rgb,
      coatingType
    );
  }
}
