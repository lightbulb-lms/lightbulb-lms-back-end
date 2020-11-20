package edu.uncc.itcs.lightbulblms.service;

import edu.uncc.itcs.lightbulblms.controller.model.request.CourseContentRequest;
import edu.uncc.itcs.lightbulblms.controller.model.request.CourseMemberAssignmentRequest;
import edu.uncc.itcs.lightbulblms.controller.model.request.CreateCourseRequest;
import edu.uncc.itcs.lightbulblms.controller.model.response.*;
import edu.uncc.itcs.lightbulblms.repo.CourseContentRepo;
import edu.uncc.itcs.lightbulblms.repo.CourseMemberRepo;
import edu.uncc.itcs.lightbulblms.repo.CourseRepo;
import edu.uncc.itcs.lightbulblms.repo.model.CourseContentEntity;
import edu.uncc.itcs.lightbulblms.repo.model.CourseEntity;
import edu.uncc.itcs.lightbulblms.repo.model.CourseMemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepo courseRepo;
    private final CourseMemberRepo courseMemberRepo;
    private final UserService userService;
    private final CourseContentRepo courseContentRepo;

    @Autowired
    public CourseService(CourseRepo courseRepo, CourseMemberRepo courseMemberRepo, UserService userService, CourseContentRepo courseContentRepo) {
        this.courseRepo = courseRepo;
        this.courseMemberRepo = courseMemberRepo;
        this.userService = userService;
        this.courseContentRepo = courseContentRepo;
    }

    public MultipleCoursesResponse getAllCourses() {
        MultipleCoursesResponse response = new MultipleCoursesResponse();
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

    public MultipleCoursesResponse getCoursesForUserId(String userId) {
        List<CourseMemberEntity> courseMemberEntities = courseMemberRepo.findByUserId(userId);
        List<CourseEntity> courses = courseMemberEntities.stream()
                .map(memberEntity -> memberEntity.getCourse())
                .collect(Collectors.toList());
        MultipleCoursesResponse response = new MultipleCoursesResponse();
        response.setCourses(courses);
        return response;
    }

    public CourseContentResponse getCourseContentForCourseId(Integer courseId) {
        List<CourseContentEntity> contentEntities = courseContentRepo.findByCourseId(courseId);

        return new CourseContentResponse(contentEntities.stream().map(CourseContent::new).collect(Collectors.toList()));
    }

    public CourseContent createContentForCourseId(Integer courseId, CourseContentRequest request) {
        CourseContentEntity entity = new CourseContentEntity(courseRepo.findById(courseId).get(), request);
        CourseContentEntity savedEntity = courseContentRepo.save(entity);
        return new CourseContent(entity);
    }

    public CourseContent updateContentForCourseId(Integer courseId, Integer contentId, CourseContentRequest request) {
        CourseContentEntity entity = courseContentRepo.findById(contentId).get();
        entity.setContent(request.getContent());
        entity.setLastUpdateDate(LocalDateTime.now());
        CourseContentEntity updatedEntry = courseContentRepo.save(entity);
        return new CourseContent(updatedEntry);
    }

    public void deleteCourseContent(Integer contentId) {
        courseContentRepo.deleteById(contentId);
    }
}
