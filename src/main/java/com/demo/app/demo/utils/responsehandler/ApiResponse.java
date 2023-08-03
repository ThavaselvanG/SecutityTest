package com.demo.app.demo.utils.responsehandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiResponse {
    private boolean status;
    private String message;
    private Object data;
    private int code;

}
