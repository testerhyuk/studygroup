package hyuk.studygroup.service;

import hyuk.studygroup.entity.Study;
import hyuk.studygroup.repository.StudyRepository;
import hyuk.studygroup.service.dto.request.StudyCreateRequest;
import hyuk.studygroup.service.dto.request.StudyUpdateRequest;
import hyuk.studygroup.service.dto.response.StudyResponse;
import hyuk.studygroup.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyService {
    private final Snowflake snowflake = new Snowflake();
    private final StudyRepository studyRepository;

    @Transactional
    public StudyResponse create(StudyCreateRequest request) {
        Study study = studyRepository.save(Study.create(
                snowflake.nextId(), request.getTitle(), request.getDescription(), request.getMaxMember(),
                request.isStatus(), request.getBoardId(), request.getWriterId())
        );

        return StudyResponse.from(study);
    }

    @Transactional
    public StudyResponse update(Long recruitmentId, StudyUpdateRequest request) {
        Study study = studyRepository.findById(recruitmentId).orElseThrow();

        study.update(request.getTitle(), request.getDescription(), request.getMaxMember(),
                request.isStatus());

        return StudyResponse.from(study);
    }

    @Transactional
    public void delete(Long recruitmentId) {
        studyRepository.deleteById(recruitmentId);
    }

    // 단 건 조회
    @Transactional
    public StudyResponse read(Long recruitmentId) {
        return StudyResponse.from(studyRepository.findById(recruitmentId).orElseThrow());
    }

    // 무한 스크롤 조회
    public List<StudyResponse> readAllInfiniteScroll(Long boardId, Long pageSize, Long lastRecruitmentId) {
        List<Study> recruitments = lastRecruitmentId == null ?
                studyRepository.findAllInfiniteScroll(boardId, pageSize) :
                studyRepository.findAllInfiniteScroll(boardId, pageSize, lastRecruitmentId);

        return recruitments.stream().map(StudyResponse::from).toList();
    }
}
