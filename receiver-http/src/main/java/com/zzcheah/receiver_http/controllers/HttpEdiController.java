package com.zzcheah.receiver_http.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.zzcheah.common_receiver.models.ReceiverPipeline;
import com.zzcheah.receiver_http.models.CreateTaskResponse;
import com.zzcheah.receiver_http.services.HttpEdiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("edi")
public class HttpEdiController {

    private final HttpEdiService httpEdiService;
    private final ObjectWriter objectWriter;

    public HttpEdiController(HttpEdiService taskService) {
        this.httpEdiService = taskService;
        objectWriter = new ObjectMapper().writer();
    }

    @PostMapping
    public ResponseEntity<CreateTaskResponse> createEdi(@RequestParam String client, @RequestParam String ediType) {

        ReceiverPipeline pipeline = httpEdiService.createEdi(client, ediType);
        String pipelineString;
        try {
            pipelineString = objectWriter.writeValueAsString(pipeline);
        } catch (JsonProcessingException e) {
            pipelineString = pipeline.toString();
        }

        if(pipeline.isSuccessProcessing()) {
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
