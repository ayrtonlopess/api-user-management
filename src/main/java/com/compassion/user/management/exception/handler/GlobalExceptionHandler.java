package com.compassion.user.management.exception.handler;

import com.compassion.user.management.domain.enums.ErrorType;
import com.compassion.user.management.domain.vo.ApiErrorResponseVO;
import io.swagger.v3.oas.annotations.Hidden;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {

    protected final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponseVO> handleNotFoundException(NotFoundException e) {
        log.error("notFoundException message: {} ", e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponseVO(e.getErrorType()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponseVO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("badRequestException message: {} ", e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponseVO(ErrorType.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorResponseVO> handleBadRequestException(BadRequestException e) {
        log.error("badRequestException message: {} ", e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponseVO(ErrorType.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponseVO> internalException(Exception e) {
        log.error("badRequestException message: {} ", e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponseVO(ErrorType.INTERNAL_ERROR, e.getMessage()));
    }
}
