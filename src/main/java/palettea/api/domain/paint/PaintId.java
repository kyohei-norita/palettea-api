package palettea.api.domain.paint;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.UUID;

@Value(staticConstructor = "reconstruct")
@Accessors(fluent = true, makeFinal = true)
public class PaintId {
  @NonNull UUID value;
}
