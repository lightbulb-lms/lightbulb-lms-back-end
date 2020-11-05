package edu.uncc.itcs.lightbulblms.repo;

import edu.uncc.itcs.lightbulblms.repo.model.CourseMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseMemberRepo extends JpaRepository<CourseMemberEntity, Integer> {
    List<CourseMemberEntity> findByCourseId(Integer courseId);

    @Transactional
    Integer deleteByCourseIdAndUserId(Integer courseId, String userId);
}
