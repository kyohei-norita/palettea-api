package palettea.api.domain.paint;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

@Value(staticConstructor = "create")
@Accessors(fluent = true, makeFinal = true)
public class PaintName {
  @NonNull String value;
}
