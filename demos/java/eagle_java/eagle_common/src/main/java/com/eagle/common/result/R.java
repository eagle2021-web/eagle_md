package com.eagle.common.result;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

@Data
@SuppressWarnings("unused")
@Builder
public class R {

    private Integer code;
    private String message;
    private Map<String, Object> data;

    public R httpStatus(HttpStatus s) {
        code = s.value();
        message = s.getReasonPhrase();
        return this;
    }

    public R ServiceStatus(ServiceStatus s) {
        code = s.value();
        message = s.getReasonPhrase();
        return this;
    }

    public R ok() {
        return httpStatus(HttpStatus.OK);
    }
}
