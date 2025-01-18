package com.compassion.user.management.handler;

import com.compassion.user.management.exception.handler.GlobalExceptionHandler;
import com.compassion.user.management.exception.handler.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testHandleNotFound() {
        var exception = new NotFoundException("NOT_FOUND");

        var response = handler.handleNotFoundException(exception);

        assertEquals(404, response.getStatusCode().value());
        assertEquals("NOT_FOUND", response.getBody().code());

    }

    @Test
    void testHandleBadRequestException() {
        var errorMessage = "Invalid input provided";
        var exception = new BadRequestException(errorMessage);

        var response = handler.handleBadRequestException(exception);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
        assertEquals(errorMessage, response.getBody().message());
    }

    @Test
    void testInternalException() {
        var errorMessage = "An unexpected error occurred";
        var exception = new Exception(errorMessage);

        var response = handler.internalException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode().value());
        assertEquals(errorMessage, response.getBody().message());
    }
}
