package hyuk.studygroup.controller;

import hyuk.studygroup.service.StudyService;
import hyuk.studygroup.service.dto.request.StudyCreateRequest;
import hyuk.studygroup.service.dto.request.StudyUpdateRequest;
import hyuk.studygroup.service.dto.response.StudyResponse;
import hyuk.studygroup.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudyController {
    private final Snowflake snowflake = new Snowflake();
    private final StudyService studyService;

    // 단 건 조회
    @GetMapping("/v1/study-recruitment/{recruitmentId}")
    public StudyResponse read(@PathVariable("recruitmentId") Long recruitmentId) {
        return studyService.read(recruitmentId);
    }

    // 스터디 모집 생성
    @PostMapping("/v1/study-recruitment")
    public StudyResponse create(@RequestBody StudyCreateRequest request) {
        return studyService.create(request);
    }

    // 스터디 모집 수정
    @PutMapping("/v1/study-recruitment/{recruitmentId}")
    public StudyResponse update(@PathVariable("recruitmentId") Long recruitmentId,
                                @RequestBody StudyUpdateRequest request) {
        return studyService.update(recruitmentId, request);
    }

    // 스터디 모집 삭제
    @DeleteMapping("/v1/study-recruitment/{recruitmentId}")
    public void delete(@PathVariable("recruitmentId") Long recruitmentId) {
        studyService.delete(recruitmentId);
    }

    // 무한 스크롤 조회
    @GetMapping("/v1/articles/infinite-scroll")
    public List<StudyResponse> readAllInfiniteScroll(
            @RequestParam("boardId") Long boardId,
            @RequestParam("pageSize") Long pageSize,
            @RequestParam(value = "lastRecruitmentId", required = false) Long lastRecruitmentId
    ) {
        return studyService.readAllInfiniteScroll(boardId, pageSize, lastRecruitmentId);
    }
}
