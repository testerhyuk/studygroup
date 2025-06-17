package hyuk.bookmark.service.dto.response;

import hyuk.bookmark.entity.Bookmark;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class BookmarkResponse {
    private Long bookmarkId;
    private Long recruitmentId;
    private Long userId;
    private LocalDateTime createdAt;

    public static BookmarkResponse from(Bookmark bookmark) {
        BookmarkResponse response = new BookmarkResponse();

        response.bookmarkId = bookmark.getBookmarkId();
        response.recruitmentId = bookmark.getRecruitmentId();
        response.userId = bookmark.getUserId();
        response.createdAt = bookmark.getCreatedAt();

        return response;
    }
}
