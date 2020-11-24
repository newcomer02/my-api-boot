package cn.peiyi.lin.common.jwt;

import cn.peiyi.lin.annotation.JwtIgnore;
import cn.peiyi.lin.common.base.Constant;
import cn.peiyi.lin.common.exception.BusinessException;
import cn.peiyi.lin.properties.JwtProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 忽略带 @JwtIgnore 的请求，不继续验证
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                return true;
            }
        }

        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        // 获取请求头
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        log.info("## authHeader = {}", authHeader);

        if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            log.info("### 用户未登录，请先登录 ###");
            throw new BusinessException(Constant.REP_AUT_FAIL, "用户未登录，请先登录");
        }

        // 获取 token
        final String token = authHeader.substring(7);
        if (jwtProperties == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            jwtProperties = (JwtProperties) factory.getBean("jwtProperties");
        }

        // 解析 验证 token
        JwtTokenUtil.parseJWT(token, jwtProperties.getBase64Secret());

        return true;
    }
}
