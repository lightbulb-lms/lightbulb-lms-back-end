package edu.uncc.itcs.lightbulblms.service;

import edu.uncc.itcs.lightbulblms.controller.model.request.CourseMemberAssignmentRequest;
import edu.uncc.itcs.lightbulblms.controller.model.request.CreateCourseRequest;
import edu.uncc.itcs.lightbulblms.controller.model.response.AllCoursesResponse;
import edu.uncc.itcs.lightbulblms.controller.model.response.CourseMember;
import edu.uncc.itcs.lightbulblms.controller.model.response.CourseMembersResponse;
import edu.uncc.itcs.lightbulblms.repo.CourseMemberRepo;
import edu.uncc.itcs.lightbulblms.repo.CourseRepo;
import edu.uncc.itcs.lightbulblms.repo.model.CourseEntity;
import edu.uncc.itcs.lightbulblms.repo.model.CourseMemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepo courseRepo;
    private final CourseMemberRepo courseMemberRepo;
    private final UserService userService;

    @Autowired
    public CourseService(CourseRepo courseRepo, CourseMemberRepo courseMemberRepo, UserService userService) {
        this.courseRepo = courseRepo;
        this.courseMemberRepo = courseMemberRepo;
        this.userService = userService;
    }

    public AllCoursesResponse getAllCourses() {
        AllCoursesResponse response = new AllCoursesResponse();
        response.setCourses(courseRepo.findAll());
        return response;
    }

    public CourseEntity createNewCourse(CreateCourseRequest request) {
        CourseEntity newCourse = new CourseEntity(request);
        return courseRepo.save(newCourse);
    }

    public CourseMembersResponse getCourseMembersForCourseID(Integer courseId) {
        List<CourseMemberEntity> courseMemberEntities = courseMemberRepo.findByCourseId(courseId);
        Map<String, CourseMember> usersMap = userService.getUsersMap();

        List<CourseMember> courseMembers = courseMemberEntities.stream()
                .map(courseMemberEntity -> usersMap.get(courseMemberEntity.getUserId()))
                .collect(Collectors.toList());

        courseMembers.removeIf(Objects::isNull);

        return new CourseMembersResponse(courseMembers);
    }

    public CourseMemberEntity assignUserToCourse(Integer courseId, CourseMemberAssignmentRequest request) {
        CourseEntity course = courseRepo.findById(courseId).get();
        return courseMemberRepo.save(new CourseMemberEntity(course, request));
    }

    public void removeUserFromCourse(Integer courseId, CourseMemberAssignmentRequest request) {
        courseMemberRepo.deleteByCourseIdAndUserId(courseId, request.getUserId());
    }
}
