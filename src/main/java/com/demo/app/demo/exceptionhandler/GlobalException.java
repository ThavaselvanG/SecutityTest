package com.demo.app.demo.exceptionhandler;

import com.demo.app.demo.utils.responsehandler.ApiResponse;
import com.demo.app.demo.utils.responsehandler.ResponseHandler;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalException {
    @Autowired
    private ResponseHandler responseHandler;
    //        LOG.error("Responding with access denied error. Message - {}", accessDeniedException.getMessage());

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ApiResponse> authHandler(AuthenticationException exception) {
        return responseHandler.unauthorized(exception.getMessage());
    }
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiResponse> accessDenied(AccessDeniedException exception) {
        return responseHandler.unauthorized(exception.getMessage());
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ApiResponse> resourceNotFound(UsernameNotFoundException exception) {
        return responseHandler.notFound(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse> dataAccessException(DataAccessException exception) {
        String sqlError = "";
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null) {
            sqlError = exception.getRootCause().getMessage();
        } else sqlError = exception.getMessage();
        return responseHandler.badResponse(sqlError);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse> nullPointerException(NullPointerException exception) {
        return responseHandler.customErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<ApiResponse> indexOutOfBoundsException(IndexOutOfBoundsException exception) {
        return responseHandler.customErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse> validationSpring(ConstraintViolationException exception) {
        Set<String> errors = new HashSet<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        return errors.size() == 1 ? responseHandler.badResponse((String) errors.toArray()[0]) : responseHandler.badResponse(errors.toString());
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse> validation(MethodArgumentNotValidException exception) {
        Set<String> errorMessages = exception.getBindingResult().getAllErrors()
                .stream().filter(objectError -> objectError.contains(ConstraintViolation.class))
                .map(objectError -> objectError.unwrap(ConstraintViolation.class))
                .map(ConstraintViolation::getMessage).collect(Collectors.toSet());
        return errorMessages.size() == 1 ? responseHandler.badResponse((String) errorMessages.toArray()[0]) : responseHandler.badResponse( errorMessages.toString());
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse> resourceNotFound(ResourceNotFound exception) {
        return responseHandler.customErrorResponse(HttpStatus.BAD_REQUEST   , exception.getMessage());
    }



}
