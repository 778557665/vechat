package com.wengzhoujun.vechat.handler;

import com.wengzhoujun.vechat.domain.Result;
import com.wengzhoujun.vechat.util.ResponseUtil;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result jsonErrorHandler(Exception e) {
        String statusCode;
        String msg;
        if (e instanceof MissingServletRequestParameterException) {
            statusCode = "miss_parameter";
            msg = e.getMessage();
        } else if (e instanceof ConstraintViolationException || e instanceof BindException) {
            statusCode = "validated_error";
            msg = e.getMessage();
        }else if (e instanceof HttpRequestMethodNotSupportedException){
            statusCode = "method_not_supported";
            msg = e.getMessage();
        } else {
            statusCode = "system_error";
            msg = "system error!";
        }
        e.printStackTrace();
        return ResponseUtil.createResult(false, statusCode, msg);
    }

}