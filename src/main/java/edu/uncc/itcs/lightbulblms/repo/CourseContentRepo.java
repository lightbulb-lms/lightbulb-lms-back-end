package edu.uncc.itcs.lightbulblms.repo;

import edu.uncc.itcs.lightbulblms.repo.model.CourseContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseContentRepo extends JpaRepository<CourseContentEntity, Integer> {
    List<CourseContentEntity> findByCourseId(Integer courseId);

    CourseContentEntity findByIdAndCourseId(Integer contentId, Integer courseId);

    @Transactional
    void deleteByIdAndCourseId(Integer contentId, Integer courseId);
}
