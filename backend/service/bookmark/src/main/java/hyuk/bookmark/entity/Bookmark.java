package hyuk.bookmark.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookmark")
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {
    @Id
    private Long bookmarkId;
    private Long recruitmentId;
    private Long userId;
    private LocalDateTime createdAt;

    public static Bookmark create(Long bookmarkId, Long recruitmentId, Long userId) {
        Bookmark bookmark = new Bookmark();

        bookmark.bookmarkId = bookmarkId;
        bookmark.recruitmentId = recruitmentId;
        bookmark.userId = userId;
        bookmark.createdAt = LocalDateTime.now();

        return bookmark;
    }
}
