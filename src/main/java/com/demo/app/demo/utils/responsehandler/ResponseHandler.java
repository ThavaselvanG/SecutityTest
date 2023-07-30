package com.demo.app.demo.utils.responsehandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("apiHandler")
public class ResponseHandler {
    public ResponseEntity<ApiResponse> successResponse(Object data) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("success")
                .status(true)
                .data(data).build();
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ApiResponse> customResponse(HttpStatus httpStatus, boolean status, String msg, Object data) {
        ApiResponse response = ApiResponse.builder()
                .code(httpStatus.value())
                .status(status)
                .message(msg)
                .data(data).build();
        return new ResponseEntity<>(response, httpStatus);
    }

    public ResponseEntity<ApiResponse> badResponse(String msg) {
        ApiResponse response = ApiResponse.builder()
                .code(400)
                .message(msg)
                .status(false)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse> unauthorized(String msg) {
        ApiResponse response = ApiResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .status(false)
                .message(msg).build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<ApiResponse> notFound(String msg) {
        ApiResponse response = ApiResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(false)
                .message(msg).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
