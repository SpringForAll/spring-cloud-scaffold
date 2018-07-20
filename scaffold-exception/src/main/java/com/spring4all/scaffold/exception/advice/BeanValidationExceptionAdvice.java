package com.spring4all.scaffold.exception.advice;

import com.spring4all.scaffold.common.BaseErrorCode;
import com.spring4all.scaffold.common.BaseResult;
import java.util.HashMap;
import java.util.Map;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author fangzhibin
 */
@RestControllerAdvice
public class BeanValidationExceptionAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
  public BaseResult<Map<String, String>> methodArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    BindingResult result = exception.getBindingResult();
    Map<String, String> errorMap = new HashMap<>();
    for (ObjectError error : result.getAllErrors()) {
      String message = error.getDefaultMessage();
      String objectName = error.getObjectName();
      if (null == objectName || objectName.isEmpty()) {
        message += ",";
      }
      if (error instanceof FieldError) {
        String field = ((FieldError) error).getField();
        if (null == field || field.isEmpty()) {
          message += ",";
        }
        errorMap.put(objectName + field, message);
      } else {
        String code = error.getCode();
        errorMap.put(objectName + code, message);
      }
    }
    return new BaseResult<>(BaseErrorCode.PARAMETER_ILLEGAL.getCode(),
        BaseErrorCode.PARAMETER_ILLEGAL.getMsg(), errorMap);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public BaseResult<Map<String, String>> constraintViolationException(
      ConstraintViolationException exception) {
    Map<String, String> errorMap = new HashMap<>();
    for (ConstraintViolation<?> error : exception.getConstraintViolations()) {
      String code = error.getPropertyPath().toString();
      String message = error.getMessage();
      errorMap.put(code, message);
    }
    return new BaseResult<>(BaseErrorCode.PARAMETER_ILLEGAL.getCode(),
        BaseErrorCode.PARAMETER_ILLEGAL.getMsg(), errorMap);
  }

}
