package uk.gov.hmcts.reform.dev.mappers;

import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.dtos.response.TaskResponse;

public class TaskMapper {
    public static TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate()
        );
    }
}