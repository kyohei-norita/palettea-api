package palettea.api.domain.manufacturer;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

@Value(staticConstructor = "reconstruct")
@Accessors(fluent = true, makeFinal = true)
public class ManufacturerName {
  @NonNull String value;
}
