package com.lnlib.springboot.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse
{
    private int code;
    private String message;
}
