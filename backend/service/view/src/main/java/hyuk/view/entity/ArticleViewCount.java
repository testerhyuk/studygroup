package hyuk.view.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "article_view_count")
public class ArticleViewCount {
    @Id
    private Long recruitmentId;
    private Long viewCount;

    public static ArticleViewCount init(Long recruitmentId, Long viewCount) {
        ArticleViewCount articleViewCount = new ArticleViewCount();

        articleViewCount.recruitmentId = recruitmentId;
        articleViewCount.viewCount = viewCount;

        return articleViewCount;
    }
}
