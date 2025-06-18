package hyuk.view.service;

import hyuk.view.repository.ArticleViewCountRepository;
import hyuk.view.repository.ArticleViewDistributedLockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ArticleViewService {
    private final ArticleViewCountRepository articleViewCountRepository;
    private final ArticleViewCountBackUpProcessor articleViewCountBackUpProcessor;
    private final ArticleViewDistributedLockRepository articleViewDistributedLockRepository;
    private static final int BACK_UP_BATCH_SIZE = 100;
    private static final Duration TTl = Duration.ofMinutes(10);

    public Long increase(Long recruitmentId, Long userId) {
        if(!articleViewDistributedLockRepository.lock(recruitmentId, userId, TTl)) {
            return articleViewCountRepository.read(recruitmentId);
        }

        Long count = articleViewCountRepository.increase(recruitmentId);

        if (count % BACK_UP_BATCH_SIZE == 0) {
            articleViewCountBackUpProcessor.backUp(recruitmentId, count);
        }
        return count;
    }

    // 특정 게시글의 현재 조회수
    public Long count(Long recruitmentId) {
        return articleViewCountRepository.read(recruitmentId);
    }
}
