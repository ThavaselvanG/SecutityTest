package com.demo.app.demo.utils.responsehandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("apiHandler")
public class ResponseHandler {
    public ResponseEntity<ApiResponse> successResponse(Object data) {
        return ResponseEntity.ok().body(ApiResponse.builder()
                .data(data).build());
    }

    public ResponseEntity<ApiResponse> customErrorResponse(HttpStatus httpStatus , String msg ) {
        return new ResponseEntity<>( ApiResponse.builder()
                .code(httpStatus.value())
                .message(msg)
               .build(), httpStatus);
    }

    public ResponseEntity<ApiResponse> badResponse(String msg) {
        ApiResponse response = ApiResponse.builder()
                .code(400)
                .message(msg)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse> unauthorized(String msg) {
        ApiResponse response = ApiResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                 .message(msg).build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<ApiResponse> notFound(String msg) {
        ApiResponse response = ApiResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                 .message(msg).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
