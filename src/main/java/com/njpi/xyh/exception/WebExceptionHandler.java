package com.njpi.xyh.exception;

import com.njpi.xyh.common.Result;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebExceptionHandler {


    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result customerException(ConstraintViolationException e) {
        System.out.println(e.getMessage());
        return Result.error(e.getMessage());
    }


}