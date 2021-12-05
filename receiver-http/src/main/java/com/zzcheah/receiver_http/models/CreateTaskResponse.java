package com.zzcheah.receiver_http.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTaskResponse {

    private String traceId;
    private String info;
    private String errMsg;
    private String details;
    private Map<String,Object> extra;

}
