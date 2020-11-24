package cn.peiyi.lin.component.interceptor;

import cn.peiyi.lin.common.base.BaseResult;
import cn.peiyi.lin.common.base.Rsp;
import cn.peiyi.lin.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 未被定义的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResult defaultExceptionHandler(Exception e) {
        log.error("Exception {}", e);
        return Rsp.fail(e.getMessage(), null);
    }

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public BaseResult businessExceptionHandler(BusinessException e) {
        log.error("BusinessException {}", e);
        return Rsp.fail(e.getCode(), e.getMessage(), null);
    }

    /**
     * 处理请求对象属性不满足校验规则的异常信息
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResult exception(HttpServletRequest request, MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();
        for (FieldError error : fieldErrors) {
            builder.append(error.getDefaultMessage());
        }
        return Rsp.fail(builder.toString(), null);
    }

    /**
     * 处理请求单个参数不满足校验规则的异常信息
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public BaseResult constraintViolationExceptionHandler(HttpServletRequest request, ConstraintViolationException exception) {
        return Rsp.fail(exception.getMessage(), null);
    }
}
