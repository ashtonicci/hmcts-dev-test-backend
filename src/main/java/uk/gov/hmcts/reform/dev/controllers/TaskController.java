package uk.gov.hmcts.reform.dev.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import uk.gov.hmcts.reform.dev.services.TaskService;
import uk.gov.hmcts.reform.dev.dtos.request.CreateTaskRequest;
import uk.gov.hmcts.reform.dev.dtos.request.UpdateTaskStatusRequest;
import uk.gov.hmcts.reform.dev.dtos.response.TaskResponse;

import java.util.UUID;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Tag(name = "Tasks", description = "Task management API")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(
            @Valid @RequestBody CreateTaskRequest request
    ) {
        TaskResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/tasks/" + response.getId())
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable UUID id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateStatus(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTaskStatusRequest request
    ) {
        return ResponseEntity.ok(service.updateStatus(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}