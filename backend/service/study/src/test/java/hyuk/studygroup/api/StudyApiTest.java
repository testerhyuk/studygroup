package hyuk.studygroup.api;

import hyuk.studygroup.service.dto.request.StudyCreateRequest;
import hyuk.studygroup.service.dto.request.StudyUpdateRequest;
import hyuk.studygroup.service.dto.response.StudyResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

@Log4j2
public class StudyApiTest {
    RestClient restClient = RestClient.create("http://localhost:9010");

    @Test
    void createTest() {
        StudyResponse response = create(new StudyCreateRequest(
                "test1", "test desc1", 5, true, 1L, 1L
        ));

        log.info("response = " + response);
    }

    @Test
    void readTest() {
        StudyResponse response = read(193313431438798848L);

        log.info("response = " + response);
    }

    @Test
    void updateTest() {
        update(193313431438798848L);

        StudyResponse response = read(193313431438798848L);

        log.info("response = " + response);
    }

    void update(Long recruitmentId) {
        restClient.put()
                .uri("/v1/study-recruitment/{recruitmentId}", recruitmentId)
                .body(new StudyUpdateRequest("update test1", "update test desc1", 5, true))
                .retrieve()
                .toBodilessEntity();
    }

    @Test
    void deleteTest() {
        restClient.delete()
                .uri("/v1/study-recruitment/{recruitmentId}", 193313431438798848L)
                .retrieve()
                .toBodilessEntity();
    }

    @Test
    void readAllInfiniteScrollTest() {
        List<StudyResponse> recruitment1 = restClient.get()
                .uri("/v1/articles/infinite-scroll?boardId=1&pageSize=5")
                .retrieve()
                .body(new ParameterizedTypeReference<List<StudyResponse>>() {});

        log.info("firstPage");

        for (StudyResponse studyResponse : recruitment1) {
            log.info("studyResponse.getRecruitmentId() = " + studyResponse.getRecruitmentId());
        }

        Long lastRecruitmentId = recruitment1.getLast().getRecruitmentId();

        List<StudyResponse> recruitment2 = restClient.get()
                .uri("/v1/articles/infinite-scroll?boardId=1&pageSize=5&lastRecruitmentId=%s"
                        .formatted(lastRecruitmentId))
                .retrieve()
                .body(new ParameterizedTypeReference<List<StudyResponse>>() {});

        log.info("secondPage");

        for (StudyResponse studyResponse : recruitment2) {
            log.info("studyResponse.getRecruitmentId() = " + studyResponse.getRecruitmentId());
        }
    }

    StudyResponse create(StudyCreateRequest request) {
        return restClient.post()
                .uri("/v1/study-recruitment")
                .body(request)
                .retrieve()
                .body(StudyResponse.class);
    }



    StudyResponse read(Long recruitmentId) {
        return restClient.get()
                .uri("/v1/study-recruitment/{recruitmentId}", recruitmentId)
                .retrieve()
                .body(StudyResponse.class);
    }

    @Getter
    @AllArgsConstructor
    static class StudyCreateRequest {
        private String title;
        private String description;
        private int maxMember;
        private boolean status;
        private Long boardId;
        private Long writerId;
    }

    @Getter
    @AllArgsConstructor
    static class StudyUpdateRequest {
        private String title;
        private String description;
        private int maxMember;
        private boolean status;
    }
}
