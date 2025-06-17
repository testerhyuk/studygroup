package hyuk.studygroup.repository;

import hyuk.studygroup.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study, Long> {
    @Query(
            value = "select study.recruitment_id, study.title, study.description, study.max_member, study.status, " +
                    "study.board_id, study.writer_id, study.created_at, study.modified_at " +
                    "from study " +
                    "where board_id = :boardId " +
                    "order by recruitment_id desc limit :limit",
            nativeQuery = true
    )
    List<Study> findAllInfiniteScroll(@Param("boardId") Long boardId, @Param("limit") Long limit);

    @Query(
            value = "select study.recruitment_id, study.title, study.description, study.max_member, study.status, " +
                    "study.board_id, study.writer_id, study.created_at, study.modified_at " +
                    "from study " +
                    "where board_id = :boardId and recruitment_id < :lastRecruitmentId " +
                    "order by recruitment_id desc limit :limit",
            nativeQuery = true
    )
    List<Study> findAllInfiniteScroll(
            @Param("boardId") Long boardId,
            @Param("limit") Long limit,
            @Param("lastRecruitmentId") Long lastRecruitmentId
    );
}
