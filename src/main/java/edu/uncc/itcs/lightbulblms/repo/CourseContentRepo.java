package edu.uncc.itcs.lightbulblms.repo;

import edu.uncc.itcs.lightbulblms.repo.model.CourseContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseContentRepo extends JpaRepository<CourseContentEntity, Integer> {
    List<CourseContentEntity> findByCourseId(Integer courseId);
}
