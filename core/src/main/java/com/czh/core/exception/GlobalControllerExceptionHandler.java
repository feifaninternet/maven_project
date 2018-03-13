package com.czh.core.exception;

import com.czh.core.constant.HttpMessageConstant;
import com.czh.core.utils.ResponseUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * 全局的异常处理
 *
 * @author Administrator
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> systemException(Exception ex) {
        logger.info("ex:" + ex.getMessage());
        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR, HttpMessageConstant.HTTP_MESSAGE_INTERNAL_SERVER_ERROR, ex.getMessage());
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<Object> exception(Exception ex) {
        logger.info("ex:" + ex.getMessage());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST, HttpMessageConstant.HTTP_MESSAGE_BAG_REQUEST, ex.getMessage());
    }

    // valid exception

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder errorMesssage = new StringBuilder("Invalid Request:");
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (int i = 0; i < fieldErrors.size(); i++) {
            FieldError fieldError = fieldErrors.get(i);
            if (i == fieldErrors.size() - 1) {
                errorMesssage.append(fieldError.getDefaultMessage());
            } else {
                errorMesssage.append(fieldError.getDefaultMessage()).append(", ");
            }
        }

        logger.error(bindingResult.getFieldError().getDefaultMessage());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST, HttpMessageConstant.HTTP_MESSAGE_BAG_REQUEST, errorMesssage.toString());
    }

    // missing Parameter
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseUtil.error(HttpStatus.BAD_REQUEST, HttpMessageConstant.HTTP_MESSAGE_BAG_REQUEST, ex.getMessage());
    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MySQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMySQLIntegrityConstraintViolationException(
            MySQLIntegrityConstraintViolationException ex) {
        logger.error(ex.getMessage(), ex);
        int errorCode = ex.getErrorCode();
        String localizedMessage = ex.getLocalizedMessage();
        logger.error("errorCode", errorCode);
        logger.error("localizedMessage", localizedMessage);
        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR, HttpMessageConstant.HTTP_MESSAGE_INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public ResponseEntity<Object> handleDuplicateKeyException(
            DuplicateKeyException ex) {
        logger.error(ex.getMessage(), ex);
        Throwable throwable = ex.getCause();
        if (throwable instanceof MySQLIntegrityConstraintViolationException) {
            MySQLIntegrityConstraintViolationException cause = (MySQLIntegrityConstraintViolationException) throwable;
            String sqlState = cause.getSQLState();
            return ResponseUtil.error(HttpStatus.BAD_REQUEST, HttpMessageConstant.HTTP_MESSAGE_BAG_REQUEST, sqlState);
        }
        return ResponseUtil.error(HttpStatus.BAD_REQUEST, HttpMessageConstant.HTTP_MESSAGE_BAG_REQUEST, ex.getMessage());
    }
}
