package edu.uncc.itcs.lightbulblms.controller.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateCourseRequest {
    @NotBlank(message = "courseTitle cannot be blank")
    private String title;

    @NotBlank(message = "courseDescription cannot be blank")
    private String description;

    @NotBlank(message = "courseCode cannot be blank")
    private String courseCode;
}
