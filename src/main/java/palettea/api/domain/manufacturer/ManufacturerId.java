package palettea.api.domain.manufacturer;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.UUID;

@Value(staticConstructor = "reconstruct")
@Accessors(fluent = true, makeFinal = true)
public class ManufacturerId {
  @NonNull UUID value;
}
