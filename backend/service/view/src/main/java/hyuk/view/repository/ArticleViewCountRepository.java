package hyuk.view.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleViewCountRepository {
    private final StringRedisTemplate redisTemplate;
    private static final String KEY_FORMAT = "view::recruitment::%s::view_count";

    public Long read(Long recruitmentId) {
        String result = redisTemplate.opsForValue().get(generateKey(recruitmentId));

        return result == null ? 0L : Long.parseLong(result);
    }

    public Long increase(Long recruitmentId) {
        return redisTemplate.opsForValue().increment(generateKey(recruitmentId));
    }

    private String generateKey(Long recruitmentId) {
        return KEY_FORMAT.formatted(recruitmentId);
    }
}
