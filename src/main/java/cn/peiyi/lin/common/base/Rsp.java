package cn.peiyi.lin.common.base;

/**
 * @ClassName Rsp
 * @Description baseResult 工具类
 * @Author Lin
 * @Date 2020/10/9
 * @Version 1.0
 */
public class Rsp {

    /**
     * 请求成功
     * @param msg
     * @param obj
     * @return
     */
    public static BaseResult success(String msg, Object obj) {
        return new BaseResult(Constant.REP_CODE_SUCC, msg, obj);
    }

    /**
     * 请求成功
     * @param obj
     * @return
     */
    public static BaseResult success(Object obj) {
        return success(Constant.REP_MSG_SUCC, obj);
    }

    /**
     * 请求成功
     * @return
     */
    public static BaseResult success() {
        return success(Constant.REP_MSG_SUCC, null);
    }

    /**
     * 请求失败
     * @param msg
     * @param obj
     * @return
     */
    public static BaseResult error(String msg, Object obj) {
        return new BaseResult(Constant.REP_CODE_ERR, msg, obj);
    }

    /**
     * 请求失败
     * @return
     */
    public static BaseResult error() {
        return error(Constant.REP_MSG_FAIL, null);
    }

    /**
     * 请求成功，业务失败
     * @param msg
     * @param obj
     * @return
     */
    public static BaseResult fail(String msg, Object obj) {
        return new BaseResult(Constant.REP_CODE_FAIL, msg, obj);
    }

    /**
     * 请求成功，业务失败
     * @param code
     * @param msg
     * @param obj
     * @return
     */
    public static BaseResult fail(String code, String msg, Object obj) {
        return new BaseResult(code, msg, obj);
    }

    /**
     * token 过期
     * @param msg
     * @param obj
     * @return
     */
    public static BaseResult loginAgain(String msg, Object obj) {
        return new BaseResult(Constant.REP_TOKEN_OUT, msg, obj);
    }

    /**
     * 登录失败
     * @param msg
     * @param obj
     * @return
     */
    public static BaseResult authorityFail(String msg, Object obj) {
        return new BaseResult(Constant.REP_AUT_FAIL, msg, obj);
    }

}
