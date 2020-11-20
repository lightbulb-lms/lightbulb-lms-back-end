package edu.uncc.itcs.lightbulblms.controller.model.request;

import javax.validation.constraints.NotBlank;

public class CourseContentRequest {
    @NotBlank(message = "Content is not allowed to be blank")
    private String content;

    public CourseContentRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
