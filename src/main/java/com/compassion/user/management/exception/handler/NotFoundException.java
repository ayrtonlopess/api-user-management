package com.compassion.user.management.exception.handler;

import com.compassion.user.management.domain.enums.ErrorType;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final ErrorType errorType;

    public NotFoundException() {
        super(ErrorType.NOT_FOUND.getMessage());
        this.errorType = ErrorType.NOT_FOUND;
    }

    public NotFoundException(String message) {
        super(message);
        this.errorType = ErrorType.NOT_FOUND;
    }
}
