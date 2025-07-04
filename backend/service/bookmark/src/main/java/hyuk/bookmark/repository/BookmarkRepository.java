package hyuk.bookmark.repository;

import hyuk.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByRecruitmentIdAndUserId(Long recruitmentId, Long userId);
}
