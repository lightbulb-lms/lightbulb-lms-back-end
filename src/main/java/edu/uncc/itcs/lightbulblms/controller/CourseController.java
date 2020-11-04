package edu.uncc.itcs.lightbulblms.controller;

import edu.uncc.itcs.lightbulblms.controller.annotation.AdminOperation;
import edu.uncc.itcs.lightbulblms.controller.model.request.CreateCourseRequest;
import edu.uncc.itcs.lightbulblms.controller.model.response.AllCoursesResponse;
import edu.uncc.itcs.lightbulblms.repo.CourseRepo;
import edu.uncc.itcs.lightbulblms.repo.model.Course;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class CourseController {
    private final CourseRepo courseRepo;

    @Autowired
    public CourseController(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @GetMapping("/courses")
    @ApiOperation(value = "Retrieve all courses", notes = "Retrieves all courses", response = AllCoursesResponse.class)
    @AdminOperation
    public ResponseEntity<AllCoursesResponse> getAllCourses() {
        AllCoursesResponse response = new AllCoursesResponse();
        response.setCourses(courseRepo.findAll());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/course")
    @ApiOperation(value = "Create a new course", notes = "All fields are required", response = Course.class)
    @AdminOperation
    public ResponseEntity<Course> createNewCourse(@Valid @RequestBody CreateCourseRequest request) {
        Course newCourse = new Course(request);
        return ResponseEntity.ok(courseRepo.save(newCourse));
    }
}
