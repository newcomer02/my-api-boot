package cn.peiyi.lin.config;


import cn.peiyi.lin.component.webLog.WebLogAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
    prefix = "aop.config",
    name = {"enable"},
    havingValue = "true",
    matchIfMissing = true
)
@ConditionalOnClass({WebLogAspect.class})
//@ComponentScan(
//    basePackages = "cn.peiyi.lin.component.webLog"
//)
public class AopAutoConfiguration {

    public AopAutoConfiguration() {}

    @Bean
    public WebLogAspect webLogAspect() {
        return new WebLogAspect();
    }
}
