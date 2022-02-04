package com.UMC.history.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CommonResponse<T> extends BasicResponse{
    private HttpStatus status;
    private T body;

    public CommonResponse(T data, HttpStatus status){
        this.body = data;
        this.status = status;
    }

}
