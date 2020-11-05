package edu.uncc.itcs.lightbulblms.repo;

import edu.uncc.itcs.lightbulblms.repo.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<CourseEntity, Integer> {
}
