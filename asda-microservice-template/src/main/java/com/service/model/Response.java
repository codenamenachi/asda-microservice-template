package com.service.model;

import com.service.enums.ResponseStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    private ResponseStatus status;
    private Object data;
}
