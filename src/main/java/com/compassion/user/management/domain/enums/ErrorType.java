package com.compassion.user.management.domain.enums;

import lombok.Getter;

@Getter
public enum ErrorType {

    INTERNAL_ERROR("Internal Server Error"),
    NOT_FOUND("Not found"),
    BAD_REQUEST("Bad Request");

    private final String message;

    ErrorType(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.name();
    }
}
