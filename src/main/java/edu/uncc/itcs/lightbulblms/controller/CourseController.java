package edu.uncc.itcs.lightbulblms.controller;

import edu.uncc.itcs.lightbulblms.controller.annotation.AdminOperation;
import edu.uncc.itcs.lightbulblms.controller.annotation.StudentOperation;
import edu.uncc.itcs.lightbulblms.controller.annotation.TeacherOperation;
import edu.uncc.itcs.lightbulblms.controller.model.request.CourseContentRequest;
import edu.uncc.itcs.lightbulblms.controller.model.request.CourseMemberAssignmentRequest;
import edu.uncc.itcs.lightbulblms.controller.model.request.CreateCourseRequest;
import edu.uncc.itcs.lightbulblms.controller.model.response.CourseContent;
import edu.uncc.itcs.lightbulblms.controller.model.response.CourseContentResponse;
import edu.uncc.itcs.lightbulblms.controller.model.response.CourseMembersResponse;
import edu.uncc.itcs.lightbulblms.controller.model.response.MultipleCoursesResponse;
import edu.uncc.itcs.lightbulblms.repo.CourseMemberRepo;
import edu.uncc.itcs.lightbulblms.repo.model.CourseEntity;
import edu.uncc.itcs.lightbulblms.repo.model.CourseMemberEntity;
import edu.uncc.itcs.lightbulblms.service.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Validated
public class CourseController {
    private final CourseService courseService;
    private final CourseMemberRepo courseMemberRepo;

    @Autowired
    public CourseController(CourseService courseService, CourseMemberRepo courseMemberRepo) {
        this.courseService = courseService;
        this.courseMemberRepo = courseMemberRepo;
    }

    @GetMapping("/courses")
    @ApiOperation(value = "Retrieve all courses", notes = "Retrieves all courses", response = MultipleCoursesResponse.class)
    @AdminOperation
    public ResponseEntity<MultipleCoursesResponse> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }


    @GetMapping("/assigned-courses")
    @ApiOperation(value = "Retrieve courses the user is assigned", notes = "Only returns courses for the logged in user", response = MultipleCoursesResponse.class)
    @TeacherOperation
    @StudentOperation
    public ResponseEntity<MultipleCoursesResponse> getAssignedCourses(JwtAuthenticationToken auth) {
        String userId = (String) auth.getTokenAttributes().get("uid");
        return ResponseEntity.ok(courseService.getCoursesForUserId(userId));
    }

    @PostMapping("/course")
    @ApiOperation(value = "Create a new course", notes = "All fields are required", response = CourseEntity.class)
    @AdminOperation
    public ResponseEntity<CourseEntity> createNewCourse(@Valid @RequestBody CreateCourseRequest request) {
        return ResponseEntity.ok(courseService.createNewCourse(request));
    }

    @GetMapping("/course/{courseId}/members")
    @ApiOperation(value = "Retrieve all members of a given course", response = CourseMembersResponse.class)
    @AdminOperation
    public ResponseEntity<CourseMembersResponse> getCourseMembersForCourseID(@Valid @PathVariable("courseId") @Positive @ApiParam(value = "The course ID, as retrieved by the /courses API") Integer courseId) {
        return ResponseEntity.ok(courseService.getCourseMembersForCourseID(courseId));
    }

    @PostMapping("/course/{courseId}/member")
    @ApiOperation(value = "Assign a user to a course", response = CourseMemberEntity.class)
    @AdminOperation
    public ResponseEntity<CourseMemberEntity> getCourseMembersForCourseID(@Valid
                                                                          @PathVariable("courseId")
                                                                          @Positive
                                                                          @ApiParam(value = "The course ID, as retrieved by the /courses API")
                                                                                  Integer courseId,
                                                                          @Valid @RequestBody CourseMemberAssignmentRequest request
    ) {
        return ResponseEntity.ok(courseService.assignUserToCourse(courseId, request));
    }

    @DeleteMapping("/course/{courseId}/member")
    @ApiOperation(value = "Remove a user from a course. Returns 204 no content on success")
    @AdminOperation
    public ResponseEntity removeMemberFromCourse(@Valid
                                                 @PathVariable("courseId")
                                                 @Positive
                                                 @ApiParam(value = "The course ID, as retrieved by the /courses API")
                                                         Integer courseId,
                                                 @Valid @RequestBody CourseMemberAssignmentRequest request
    ) {
        courseService.removeUserFromCourse(courseId, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course/{courseId}/content")
    @ApiOperation(value = "Retrieve content for a given course", response = CourseContentResponse.class)
    @TeacherOperation
    @StudentOperation
    public ResponseEntity<CourseContentResponse> getCourseContentForCourseID(@Valid @PathVariable("courseId") @Positive @ApiParam(value = "The course ID, as retrieved by the /courses API") Integer courseId, JwtAuthenticationToken auth) {
        if (courseMemberRepo.existsByCourseIdAndUserId(courseId, (String) auth.getTokenAttributes().get("uid"))) {
            return ResponseEntity.ok(courseService.getCourseContentForCourseId(courseId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/course/{courseId}/content")
    @ApiOperation(value = "Create a new piece of content content for a given course", response = CourseContent.class)
    @TeacherOperation
    public ResponseEntity<CourseContent> createNewCourseContentForCourseID(@Valid @PathVariable("courseId") @Positive @ApiParam(value = "The course ID, as retrieved by the /courses API") Integer courseId,
                                                                           @Valid @RequestBody CourseContentRequest request,
                                                                           JwtAuthenticationToken auth) {
        if (courseMemberRepo.existsByCourseIdAndUserId(courseId, (String) auth.getTokenAttributes().get("uid"))) {
            return ResponseEntity.ok(courseService.createContentForCourseId(courseId, request));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/course/{courseId}/content/{contentId}")
    @ApiOperation(value = "Update a piece of content content for a given course", response = CourseContent.class)
    @TeacherOperation
    public ResponseEntity<CourseContent> updateCourseContentForCourseID(@Valid @PathVariable("courseId") @Positive @ApiParam(value = "The course ID, as retrieved by the /courses API") Integer courseId,
                                                                        @Valid @PathVariable("contentId") @Positive @ApiParam(value = "The content ID, as retrieved by the /course/{courseId}/content API") Integer contentId,
                                                                        @Valid @RequestBody CourseContentRequest request,
                                                                        JwtAuthenticationToken auth) {
        if (courseMemberRepo.existsByCourseIdAndUserId(courseId, (String) auth.getTokenAttributes().get("uid"))) {
            return ResponseEntity.ok(courseService.updateContentForCourseId(courseId, contentId, request));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/course/{courseId}/content/{contentId}")
    @ApiOperation(value = "Delete a piece of content content for a given course. Returns 204 no content on success")
    @TeacherOperation
    public ResponseEntity deleteCourseContent(@Valid @PathVariable("courseId") @Positive @ApiParam(value = "The course ID, as retrieved by the /courses API") Integer courseId,
                                              @Valid @PathVariable("contentId") @Positive @ApiParam(value = "The content ID, as retrieved by the /course/{courseId}/content API") Integer contentId,
                                              JwtAuthenticationToken auth) {
        if (courseMemberRepo.existsByCourseIdAndUserId(courseId, (String) auth.getTokenAttributes().get("uid"))) {
            courseService.deleteCourseContent(contentId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
