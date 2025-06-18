package hyuk.view.service;

import hyuk.view.entity.ArticleViewCount;
import hyuk.view.repository.ArticleViewCountBackUpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ArticleViewCountBackUpProcessor {
    private final ArticleViewCountBackUpRepository articleViewCountBackUpRepository;

    @Transactional
    public void backUp(Long recruitmentId, Long viewCount) {
        int result = articleViewCountBackUpRepository.updateViewCount(recruitmentId, viewCount);

        if (result == 0) {
            articleViewCountBackUpRepository.findById(recruitmentId)
                    .ifPresentOrElse(ignored -> { },
                            () -> articleViewCountBackUpRepository.save(
                                    ArticleViewCount.init(recruitmentId, viewCount)
                            ));
        }
    }
}
