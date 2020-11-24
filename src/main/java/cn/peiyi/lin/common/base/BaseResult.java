package cn.peiyi.lin.common.base;

/**
 * @ClassName BaseResult
 * @Description 统一返回类型
 * @Author Lin
 * @Date 2020/10/9
 * @Version 1.0
 */
public class BaseResult<T> {

    private String code;

    private String message;

    private T data;

    public BaseResult() {}

    public BaseResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}