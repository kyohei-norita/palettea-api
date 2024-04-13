package palettea.api.domain.manufacturer;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(chain=true, makeFinal = true)
public class Manufacturer {
  @NonNull ManufacturerId id;
  @NonNull ManufacturerName name;
}
