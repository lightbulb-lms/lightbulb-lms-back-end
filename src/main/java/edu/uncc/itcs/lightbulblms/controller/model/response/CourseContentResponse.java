package edu.uncc.itcs.lightbulblms.controller.model.response;

import java.util.List;

public class CourseContentResponse {
    private List<CourseContent> content;

    public CourseContentResponse(List<CourseContent> content) {
        this.content = content;
    }

    public List<CourseContent> getContent() {
        return content;
    }

    public void setContent(List<CourseContent> content) {
        this.content = content;
    }
}
