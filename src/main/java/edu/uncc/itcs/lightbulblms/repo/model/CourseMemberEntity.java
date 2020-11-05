package edu.uncc.itcs.lightbulblms.repo.model;

import edu.uncc.itcs.lightbulblms.controller.model.request.CourseMemberAssignmentRequest;

import javax.persistence.*;

@Entity
@Table(name = "COURSE_MEMBER")
public class CourseMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String userId;

    @ManyToOne
    private CourseEntity course;

    public CourseMemberEntity() {
    }

    public CourseMemberEntity(CourseEntity course, CourseMemberAssignmentRequest request) {
        this.course = course;
        this.userId = request.getUserId();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }
}
