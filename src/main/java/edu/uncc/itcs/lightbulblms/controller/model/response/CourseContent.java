package edu.uncc.itcs.lightbulblms.controller.model.response;

import edu.uncc.itcs.lightbulblms.repo.model.CourseContentEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CourseContent {
    private Integer contentId;
    private String content;
    private ZonedDateTime creationTime;
    private ZonedDateTime lastUpdatedTime;

    public CourseContent(CourseContentEntity courseContentEntity) {
        this.contentId = courseContentEntity.getId();
        this.content = courseContentEntity.getContent();
        this.creationTime = courseContentEntity.getCreationDate().atZone(ZoneId.of("GMT")).withZoneSameInstant(ZoneId.of("US/Eastern"));
        this.lastUpdatedTime = courseContentEntity.getLastUpdateDate().atZone(ZoneId.of("GMT")).withZoneSameInstant(ZoneId.of("US/Eastern"));
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

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public ZonedDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(ZonedDateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
