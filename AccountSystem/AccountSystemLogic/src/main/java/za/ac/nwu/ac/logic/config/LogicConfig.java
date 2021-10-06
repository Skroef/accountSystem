package za.ac.nwu.ac.logic.config;

import com.sun.java.accessibility.util.Translator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import za.ac.nwu.ac.translator.config.TranslatorConfig;

@Import({TranslatorConfig.class})
@Configuration
public class LogicConfig {
}
