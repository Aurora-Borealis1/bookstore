package com.gongyuan.bookstore.interceptor;

import com.gongyuan.bookstore.controller.common.CommonResult;
import com.gongyuan.bookstore.controller.common.ResultCode;
import com.gongyuan.bookstore.controller.user.UserLoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * global exception handler
 *
 * @author: gongyuan
 * @date: 2024/8/10 11:33
 */
@Slf4j
@Component
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    /**
     * authentication exception handler
     *
     * @param e
     * @return
     * @see TokenValidateInterceptor
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResult<Void> handleAuthenticationException(AuthenticationException e) {
        logError(e);
        String message = e.getMessage();
        ResultCode resultCode = EnumUtils.getEnum(ResultCode.class, message);
        if (Objects.nonNull(resultCode)) {
            return CommonResult.failed(resultCode);
        }
        return CommonResult.failed(ResultCode.UNKNOWN_ERROR, message);
    }

    /**
     * handleConstraintViolationException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult<Void> handleConstraintViolationException(ConstraintViolationException e) {
        logError(e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder stringBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            stringBuilder.append(violation.getMessage()).append(" ");
        }
        return CommonResult.failed(ResultCode.ILLEGAL_ARGUMENT, stringBuilder.toString());
    }

    /**
     * validate method argument
     *
     * @param exception
     * @return
     * @see com.gongyuan.bookstore.controller.user.UserController#login(UserLoginRequest)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        logError(exception);
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError item : fieldErrors) {
            stringBuilder.append(item.getDefaultMessage()).append("\n");
        }
        return CommonResult.failed(ResultCode.ILLEGAL_ARGUMENT, stringBuilder.toString());
    }


    /**
     * unexpected exception handler
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<Void> handleThrowable(Throwable e) {
        logError(e);
        return CommonResult.failed(ResultCode.UNKNOWN_ERROR, e.getMessage());
    }

    private void logError(Throwable e) {
        log.error("[GlobalExceptionHandlerAdvice#]logError", e);
    }
}