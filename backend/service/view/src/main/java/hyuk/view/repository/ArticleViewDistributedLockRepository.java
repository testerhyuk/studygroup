package hyuk.view.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class ArticleViewDistributedLockRepository {
    private final StringRedisTemplate redisTemplate;
    private static final String KEY_FORMAT = "view::recruitment::%s::user::%s::lock";

    public boolean lock(Long recruitmentId, Long userId, Duration ttl) {
        String key = generateKey(recruitmentId, userId);

        return redisTemplate.opsForValue().setIfAbsent(key, "", ttl);
    }

    private String generateKey(Long recruitmentId, Long userId) {
        return KEY_FORMAT.formatted(recruitmentId, userId);
    }
}
