package za.ac.nwu.ac.logic.config;

import com.sun.java.accessibility.util.Translator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import za.ac.nwu.ac.translator.config.TranslatorConfig;

@Import({TranslatorConfig.class})
@Configuration
@ComponentScan(basePackages = {"za.ac.nwu.ac.logic.flow"})
public class LogicConfig {
}
