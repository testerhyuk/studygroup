package hyuk.bookmark.service;

import hyuk.bookmark.entity.Bookmark;
import hyuk.bookmark.repository.BookmarkRepository;
import hyuk.bookmark.service.dto.response.BookmarkResponse;
import hyuk.studygroup.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final Snowflake snowflake = new Snowflake();
    private final BookmarkRepository bookmarkRepository;

    public BookmarkResponse read(Long recruitmentId, Long userId) {
        return bookmarkRepository.findByRecruitmentIdAndUserId(recruitmentId, userId)
                .map(BookmarkResponse::from)
                .orElseThrow();
    }

    @Transactional
    public void bookmark(Long recruitmentId, Long userId) {
        bookmarkRepository.save(
                Bookmark.create(
                        snowflake.nextId(),
                        recruitmentId,
                        userId
                )
        );
    }

    @Transactional
    public void removeBookmark(Long recruitmentId, Long userId) {
        bookmarkRepository.findByRecruitmentIdAndUserId(recruitmentId, userId)
                .ifPresent(bookmarkRepository::delete);
    }
}
