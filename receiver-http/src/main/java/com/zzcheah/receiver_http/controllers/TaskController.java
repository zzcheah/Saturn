package com.zzcheah.receiver_http.controllers;

import com.zzcheah.receiver_http.models.CreateTaskResponse;
import com.zzcheah.receiver_http.services.TaskService;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<CreateTaskResponse> createTask(@RequestParam String from, @RequestParam String type) {
        String traceId = MDC.get("traceId");
        try {
            String result = taskService.createTask(from, type);
            return new ResponseEntity<>(CreateTaskResponse.builder()
                    .traceId(traceId)
                    .info("Successfully created task")
                    .details(result)
                    .build(), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(CreateTaskResponse.builder()
                    .traceId(traceId)
                    .errMsg(e.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
