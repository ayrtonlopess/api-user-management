package com.compassion.user.management.domain.vo;

import com.compassion.user.management.domain.enums.ErrorType;
import io.swagger.v3.oas.annotations.media.Schema;


public record ApiErrorResponseVO(@Schema(description = "Error code", example = "NOT_FOUND")
                                 String code,

                                 @Schema(description = "Error message", example = "Not found")
                                 String message) {


    public ApiErrorResponseVO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiErrorResponseVO(ErrorType errorType) {
        this(errorType.getCode(), errorType.getMessage());
    }

    public ApiErrorResponseVO(ErrorType errorType, String message) {
        this(errorType.getCode(), message != null ? message : errorType.getMessage());
    }
}
