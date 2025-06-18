package hyuk.view.api;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
public class ViewApiTest {
    RestClient restClient = RestClient.create("http://localhost:9012");

    @Test
    void viewTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            int index = i;
            executorService.submit(() -> {
                try {
                    restClient.post()
                            .uri("/v1/article-views/recruitments/{recruitmentId}/users/{userId}", 4L, 1L)
                            .retrieve()
                            .toBodilessEntity();
                    log.info("요청중... " + index);
                } catch (Exception e) {
                    log.error("요청 실패 index = " + index, e);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Long count = restClient.get()
                .uri("/v1/article-views/recruitments/{recruitmentId}/count", 4L)
                .retrieve()
                .body(Long.class);

        log.info("count = " + count);
    }
}
