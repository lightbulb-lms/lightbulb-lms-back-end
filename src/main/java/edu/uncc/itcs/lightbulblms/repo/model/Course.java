package edu.uncc.itcs.lightbulblms.repo.model;

import edu.uncc.itcs.lightbulblms.controller.model.request.CreateCourseRequest;

import javax.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String courseTitle;

    @Column
    private String courseDescription;

    @Column
    private String courseCode;

    public Course(CreateCourseRequest request) {
        this.courseTitle = request.getTitle();
        this.courseDescription = request.getDescription();
        this.courseCode = request.getCourseCode();
    }

    public Course() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
