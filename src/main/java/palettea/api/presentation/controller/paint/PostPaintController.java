package palettea.api.presentation.controller.paint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import palettea.api.application.command.paint.PaintRegisterService;
import palettea.api.application.command.paint.PaintRegisterService.Input.CoatingType;
import palettea.lib.rgb.RgbJson;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostPaintController {
  @NonNull PaintRegisterService registerService;

  @PostMapping("api/paint")
  public ResponseEntity<String> post(@RequestBody final Request request) {
    val input = new PaintRegisterService.Input(
      request.name(),
      request.manufacturerId(),
      request.rgb(),
      request.coatingType()
    );
    return registerService
      .register(input)
      .fold(
        error -> ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(error
            .map(e -> switch (e) {
              case PaintRegisterService.Error.Validation validation -> validation.value();
              case PaintRegisterService.Error.ReferentialConstraint constraint -> "referential constraint error";
            })
            .reduce((acc, current) -> acc + "," + current)
          ),
        success -> ResponseEntity.ok(success.toString())
      );

  }

  public record Request(
    @NonNull String name,
    @NonNull String manufacturerId,
    @NonNull RgbJson rgb,
    @NonNull CoatingType coatingType
  ) {}
}
