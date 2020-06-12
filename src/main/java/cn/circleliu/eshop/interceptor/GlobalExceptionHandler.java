package cn.circleliu.eshop.interceptor;

import cn.circleliu.eshop.bean.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "Server Error";
        }
        Result result = new Result();
        result.setMsg(msg);
        result.setSuccess(false);
        return result;
    }
}
