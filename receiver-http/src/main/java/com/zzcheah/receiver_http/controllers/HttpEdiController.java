package com.zzcheah.receiver_http.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzcheah.common_receiver.models.ReceiverPipeline;
import com.zzcheah.common_receiver.utils.ReceiverPipelineUtils;
import com.zzcheah.receiver_http.models.CreateTaskResponse;
import com.zzcheah.receiver_http.services.HttpEdiService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("edi")
public class HttpEdiController {

    private final HttpEdiService httpEdiService;
    private final ObjectMapper objectMapper;

    public HttpEdiController(HttpEdiService taskService) {
        this.httpEdiService = taskService;
        objectMapper = new ObjectMapper();
    }

    @PostMapping
    public ResponseEntity<CreateTaskResponse> createEdi(
            @RequestParam String client,
            @RequestParam String ediType,
            @RequestParam(required = false) String filename,
            HttpServletRequest request ) {

        ReceiverPipeline pipeline;
        try {
            pipeline = httpEdiService.createEdi(client, ediType, filename, request.getInputStream());
        } catch (IOException e) {
            return new ResponseEntity<>(CreateTaskResponse.builder()
                    .traceId(MDC.get("traceId"))
                    .errMsg("Error in Request Body")
                    .build(), HttpStatus.BAD_REQUEST);
        }
        String pipelineString;
        try {
            pipelineString = objectMapper.writeValueAsString(pipeline);
        } catch (JsonProcessingException e) {
            pipelineString = pipeline.toString();
        }

        if(ReceiverPipelineUtils.isSuccessProcessing(pipeline)) {
            return new ResponseEntity<>(CreateTaskResponse.builder()
                    .traceId(pipeline.getTraceId())
                    .info("Successfully created task")
                    .details(pipelineString)
                    .build(), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(CreateTaskResponse.builder()
                .traceId(pipeline.getTraceId())
                .errMsg(pipeline.getErrMsg())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
