package edu.uncc.itcs.lightbulblms.controller.model.response;

import edu.uncc.itcs.lightbulblms.repo.model.CourseContentEntity;

public class CourseContent {
    private Integer contentId;
    private String content;

    public CourseContent(CourseContentEntity courseContentEntity) {
        this.contentId = courseContentEntity.getId();
        this.content = courseContentEntity.getContent();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }
}
