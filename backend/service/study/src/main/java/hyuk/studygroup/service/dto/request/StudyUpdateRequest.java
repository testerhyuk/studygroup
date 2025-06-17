package hyuk.studygroup.service.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StudyUpdateRequest {
    private String title;
    private String description;
    private int maxMember;
    private boolean status;
}
