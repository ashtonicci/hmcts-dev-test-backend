package uk.gov.hmcts.reform.dev.services;

import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.dev.dtos.request.CreateTaskRequest;
import uk.gov.hmcts.reform.dev.dtos.request.UpdateTaskStatusRequest;
import uk.gov.hmcts.reform.dev.dtos.response.TaskResponse;
import uk.gov.hmcts.reform.dev.exceptions.TaskNotFoundException;
import uk.gov.hmcts.reform.dev.repositories.TaskRepository;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.models.TaskStatus;
import uk.gov.hmcts.reform.dev.mappers.TaskMapper;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskResponse create(CreateTaskRequest request) {
        Task task = new Task(
                request.getTitle(),
                request.getDescription(),
                TaskStatus.TODO,
                request.getDueDate()
        );

        Task saved = repository.save(task);

        return TaskMapper.toResponse(saved);
    }

    public TaskResponse updateStatus(UUID id, UpdateTaskStatusRequest request) {
        Task task = repository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));

        task.setStatus(request.getStatus());

        Task saved = repository.save(task);

        return TaskMapper.toResponse(saved);
    }

    public Page<TaskResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(task -> TaskMapper.toResponse(task));
    }

    public TaskResponse get(UUID id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return TaskMapper.toResponse(task);
    }   

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        repository.deleteById(id);
    }
}