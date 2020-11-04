package edu.uncc.itcs.lightbulblms.controller.model.response;

import edu.uncc.itcs.lightbulblms.repo.model.Course;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AllCoursesResponse {
    @ApiModelProperty(value = "List of courses")
    private List<Course> courses;

    public AllCoursesResponse() {
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
