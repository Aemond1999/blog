package com.hya.handler;


import com.hya.enums.HttpCodeEnum;
import com.hya.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 缺少请求参数异常
     *
     * @param ex HttpMessageNotReadableException
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result handleHttpMessageNotReadableException(
            MissingServletRequestParameterException ex) {
        return Result.fail( HttpCodeEnum.MISS_PARAM.getCode(), HttpCodeEnum.MISS_PARAM.getMsg());
    }

    /**
     * 空指针异常
     *
     * @param ex NullPointerException
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleTypeMismatchException(NullPointerException ex) {
        return Result.fail(HttpCodeEnum.NULL_POINTER.getCode(), HttpCodeEnum.NULL_POINTER.getMsg());
    }

    /**
     * 系统异常 预期以外异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleUnexpectedServer(Exception ex) {

        return Result.fail(HttpCodeEnum.SYSTEM_ERROR.getCode(), HttpCodeEnum.SYSTEM_ERROR.getMsg());

    }

}

