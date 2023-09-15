package com.edu.pm.backend.config;

import com.edu.pm.backend.commons.dto.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PMRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleConflict(Exception ex) {
        if (ex instanceof BadCredentialsException badCredentialsException) {
            return new ResponseEntity<>(new ErrorResponse(badCredentialsException.getMessage()), HttpStatus.UNAUTHORIZED);
        } else if (ex instanceof ExpiredJwtException) {
            return new ResponseEntity<>(new ErrorResponse("Authorization token has expired"), HttpStatus.UNAUTHORIZED);
        } else if (ex instanceof AccessDeniedException) {
            return new ResponseEntity<>(new ErrorResponse("No sufficient permission"), HttpStatus.FORBIDDEN);
        } else if (ex instanceof AuthenticationException) {
            return new ResponseEntity<>(new ErrorResponse("Authentication exception." + ex.getMessage()), HttpStatus.UNAUTHORIZED);
        } else if (ex instanceof DataIntegrityViolationException) {
            return new ResponseEntity<>(new ErrorResponse("Entity couldn't be added/modified/deleted."), HttpStatus.BAD_REQUEST);
        } else {
            logger.error(ex.getMessage());
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
