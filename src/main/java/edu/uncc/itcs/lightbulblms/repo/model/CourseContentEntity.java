package edu.uncc.itcs.lightbulblms.repo.model;

import edu.uncc.itcs.lightbulblms.controller.model.request.CourseContentRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "course_content")
public class CourseContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    @ManyToOne
    private CourseEntity course;

    @Column
    private String content;

    public CourseContentEntity() {
    }

    public CourseContentEntity(CourseEntity courseEntity, CourseContentRequest request) {
        this.course = courseEntity;
        this.content = request.getContent();
        this.lastUpdateDate = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
