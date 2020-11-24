package cn.peiyi.lin.config;

import cn.peiyi.lin.common.jwt.JwtInterceptor;
import cn.peiyi.lin.properties.JwtProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// 当配置文件有对应的配置时，才生效
@ConditionalOnProperty(
        prefix = "jwt.config",
        name = {"enable"},
        havingValue = "true",
        matchIfMissing = false
)
// 当该类存在时，注解的类才生效
@ConditionalOnClass({JwtInterceptor.class})
// 当 spring 为 web 服务时，才使注解的类生效
@ConditionalOnWebApplication(
        type = ConditionalOnWebApplication.Type.SERVLET
)
// 对于第三方的包, @ConfigurationProperties 注解修饰的类，使用 @Component 是无法注入的
// 需要使用 @EnableConfigurationProperties 这一注解
@EnableConfigurationProperties({JwtProperties.class})
@ComponentScan(
        basePackages = "cn.peiyi.lin.component.webConfig"
)
public class JwtAutoConfiguration {
}
