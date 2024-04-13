package palettea.api.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import palettea.lib.rgb.RgbTranslator;

@Configuration
public class RgbTranslatorConfiguration {
  @Bean RgbTranslator rgbTranslator() {return new RgbTranslator();}
}
