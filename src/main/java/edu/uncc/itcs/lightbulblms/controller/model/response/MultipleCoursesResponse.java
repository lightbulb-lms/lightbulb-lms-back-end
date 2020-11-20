package edu.uncc.itcs.lightbulblms.controller.model.response;

import edu.uncc.itcs.lightbulblms.repo.model.CourseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class MultipleCoursesResponse {
    @ApiModelProperty(value = "List of courses")
    private List<CourseEntity> courses;

    public MultipleCoursesResponse() {
    }

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }
}
