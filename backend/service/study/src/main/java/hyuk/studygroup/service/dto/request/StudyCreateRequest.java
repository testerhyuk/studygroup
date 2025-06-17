package hyuk.studygroup.service.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StudyCreateRequest {
    private String title;
    private String description;
    private int maxMember;
    private boolean status;
    private Long boardId;
    private Long writerId;
}
