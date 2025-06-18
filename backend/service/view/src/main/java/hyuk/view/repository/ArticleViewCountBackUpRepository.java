package hyuk.view.repository;

import hyuk.view.entity.ArticleViewCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleViewCountBackUpRepository extends JpaRepository<ArticleViewCount, Long> {
    @Query(
            value = "update article_view_count set view_count = :viewCount " +
                    "where recruitment_id = :recruitmentId and view_count < :viewCount",
            nativeQuery = true
    )
    @Modifying
    int updateViewCount(@Param("recruitmentId") Long recruitmentId, @Param("viewCount") Long viewCount);
}
