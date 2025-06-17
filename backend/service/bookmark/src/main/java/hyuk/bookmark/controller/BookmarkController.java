package hyuk.bookmark.controller;

import hyuk.bookmark.service.BookmarkService;
import hyuk.bookmark.service.dto.response.BookmarkResponse;
import hyuk.studygroup.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookmarkController {
    private final Snowflake snowflake = new Snowflake();
    private final BookmarkService bookmarkService;

    @GetMapping("/v1/bookmark/recruitment/{recruitmentId}/users/{userId}")
    public BookmarkResponse read(@PathVariable("recruitmentId") Long recruitmentId,
                                 @PathVariable("userId") Long userId) {
        return bookmarkService.read(recruitmentId, userId);
    }

    @PostMapping("/v1/bookmark/recruitment/{recruitmentId}/users/{userId}")
    public void bookmark(@PathVariable("recruitmentId") Long recruitmentId,
                         @PathVariable("userId") Long userId) {
        bookmarkService.bookmark(recruitmentId, userId);
    }

    @DeleteMapping("/v1/bookmark/recruitment/{recruitmentId}/users/{userId}")
    public void removeBookmark(@PathVariable("recruitmentId") Long recruitmentId,
                                @PathVariable("userId") Long userId) {
        bookmarkService.removeBookmark(recruitmentId, userId);
    }
}
