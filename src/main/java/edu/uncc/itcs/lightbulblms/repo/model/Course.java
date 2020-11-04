package edu.uncc.itcs.lightbulblms.repo.model;

import edu.uncc.itcs.lightbulblms.controller.model.request.CreateCourseRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue
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
}
