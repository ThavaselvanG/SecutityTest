package com.demo.app.demo.utils.responsehandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int code;

}
