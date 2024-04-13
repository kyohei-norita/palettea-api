package palettea.lib.rgb;

public record Rgb(
  ValidatedRgbValue r,
  ValidatedRgbValue g,
  ValidatedRgbValue b
) {
  record ValidatedRgbValue(int value) {}
}
