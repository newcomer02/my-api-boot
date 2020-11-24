package cn.peiyi.lin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Configuration
@ConditionalOnProperty(
    prefix = "exception.config",
    name = {"enable"},
    havingValue = "true",
    matchIfMissing = true
)

@ComponentScan(
        basePackages = "cn.peiyi.lin.component.interceptor",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {ControllerAdvice.class})},
        useDefaultFilters = false
)
public class ExceptionAutoConfiguration {
}
