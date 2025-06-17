package hyuk.bookmark.api;

import hyuk.bookmark.service.dto.response.BookmarkResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

@Log4j2
public class BookmarkApiTest {
    RestClient restClient = RestClient.create("http://localhost:9011");

    @Test
    void bookmarkAndRemoveBookmarkTest() {
        Long recruitmentId = 193320969540632585L;

        bookmark(recruitmentId, 1L);
        bookmark(recruitmentId, 2L);
        bookmark(recruitmentId, 3L);

        BookmarkResponse response1 = read(recruitmentId, 1L);
        BookmarkResponse response2 = read(recruitmentId, 2L);
        BookmarkResponse response3 = read(recruitmentId, 3L);

        log.info("response1 = " + response1);
        log.info("response2 = " + response2);
        log.info("response3 = " + response3);

        removeBookmark(recruitmentId, 1L);
        removeBookmark(recruitmentId, 2L);
        removeBookmark(recruitmentId, 3L);
    }

    void bookmark(Long recruitmentId, Long userId) {
        restClient.post()
                .uri("/v1/bookmark/recruitment/{recruitmentId}/users/{userId}", recruitmentId, userId)
                .retrieve()
                .toBodilessEntity();
    }

    void removeBookmark(Long recruitmentId, Long userId) {
        restClient.delete()
                .uri("/v1/bookmark/recruitment/{recruitmentId}/users/{userId}", recruitmentId, userId)
                .retrieve()
                .toBodilessEntity();
    }

    BookmarkResponse read(Long recruitmentId, Long userId) {
        return restClient.get()
                .uri("/v1/bookmark/recruitment/{recruitmentId}/users/{userId}", recruitmentId, userId)
                .retrieve()
                .body(BookmarkResponse.class);
    }
}
