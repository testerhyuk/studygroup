package hyuk.studygroup.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "study")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study {
    @Id
    private Long recruitmentId;
    private String title;
    private String description;
    private int maxMember;
    private boolean status;
    private Long boardId;
    private Long writerId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static Study create(Long recruitmentId, String title, String description,
                               int maxMember, boolean status, Long boardId, Long writerId) {
        Study study = new Study();

        study.recruitmentId = recruitmentId;
        study.title = title;
        study.description = description;
        study.maxMember = maxMember;
        study.status = status;
        study.boardId = boardId;
        study.writerId = writerId;
        study.createdAt = LocalDateTime.now();
        study.modifiedAt = study.createdAt;

        return study;
    }

    public void update(String title, String description, int maxMember, boolean status) {
        this.title = title;
        this.description = description;
        this.maxMember = maxMember;
        this.status = status;
        modifiedAt = LocalDateTime.now();
    }
}
