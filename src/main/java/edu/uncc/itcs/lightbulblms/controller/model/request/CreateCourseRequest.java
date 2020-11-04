package edu.uncc.itcs.lightbulblms.controller.model.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class CreateCourseRequest {
    @ApiModelProperty(value = "Title of course", required = true)
    @NotBlank(message = "courseTitle cannot be blank")
    private String title;

    @ApiModelProperty(value = "Description of course", required = true)
    @NotBlank(message = "courseDescription cannot be blank")
    private String description;

    @ApiModelProperty(value = "Code for course", required = true)
    @NotBlank(message = "courseCode cannot be blank")
    private String courseCode;

    public CreateCourseRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
