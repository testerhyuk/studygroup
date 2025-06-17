package hyuk.studygroup.service.dto.response;

import hyuk.studygroup.entity.Study;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class StudyResponse {
    private Long recruitmentId;
    private String title;
    private String description;
    private int maxMember;
    private boolean status;
    private Long boardId;
    private Long writerId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static StudyResponse from(Study study) {
        StudyResponse response = new StudyResponse();

        response.recruitmentId = study.getRecruitmentId();
        response.title = study.getTitle();
        response.description = study.getDescription();
        response.maxMember = study.getMaxMember();
        response.status = study.isStatus();
        response.boardId = study.getBoardId();
        response.writerId = study.getWriterId();
        response.createdAt = study.getCreatedAt();
        response.modifiedAt = study.getModifiedAt();

        return response;
    }
}
