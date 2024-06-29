package com.ufpb.meuguiaapi.controller.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StandardError {

    private Long timesStamp;

    private Integer status;

    private String error;

    public StandardError() {
    }

    public StandardError(Long timesStamp, Integer status, String error) {
        this.timesStamp = timesStamp;
        this.status = status;
        this.error = error;
    }

}
