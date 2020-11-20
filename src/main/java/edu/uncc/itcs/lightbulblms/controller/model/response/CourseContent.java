package edu.uncc.itcs.lightbulblms.controller.model.response;

import edu.uncc.itcs.lightbulblms.repo.model.CourseContentEntity;

public class CourseContent {
    private String content;

    public CourseContent(CourseContentEntity courseContentEntity) {
        this.content = courseContentEntity.getContent();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
