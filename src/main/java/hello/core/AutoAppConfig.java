package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,
                classes = Configuration.class))
//@Configuration 붙은 기존 AppConfig은 빼기 위함)
public class AutoAppConfig {
}
