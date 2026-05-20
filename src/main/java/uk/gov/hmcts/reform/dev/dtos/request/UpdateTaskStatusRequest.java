package uk.gov.hmcts.reform.dev.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import uk.gov.hmcts.reform.dev.models.TaskStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskStatusRequest {
    @NotBlank
    @Schema(example = "IN_PROGRESS")
    private TaskStatus status;
}