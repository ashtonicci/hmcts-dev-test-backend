package uk.gov.hmcts.reform.dev.dtos.request;

import lombok.*;
import uk.gov.hmcts.reform.dev.models.TaskStatus;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {

    @NotBlank
    @Schema(example = "Chicken Nuggets")
    private String title;
    @Schema(example = "Cook the goddamn chicken nuggets")
    private String description;
    @NotNull
    private LocalDateTime dueDate;
}