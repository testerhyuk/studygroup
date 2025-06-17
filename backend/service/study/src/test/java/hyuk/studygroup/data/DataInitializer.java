package hyuk.studygroup.data;

import hyuk.studygroup.entity.Study;
import hyuk.studygroup.snowflake.Snowflake;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Log4j2
public class DataInitializer {
    static final int BULK_INSERT_SIZE = 2000;
    static final int EXECUTE_COUNT = 6000;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    TransactionTemplate transactionTemplate;

    Snowflake snowflake = new Snowflake();

    CountDownLatch latch = new CountDownLatch(EXECUTE_COUNT);

    @Test
    void initialize() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < EXECUTE_COUNT; i++) {
            executorService.submit(() -> {
                insert();

                latch.countDown();

                log.info("latch.getCount() = " + latch.getCount());
            });
        }

        latch.await();
        executorService.shutdown();
    }

    void insert() {
        transactionTemplate.executeWithoutResult(status -> {
            for(int i = 0; i < BULK_INSERT_SIZE; i++) {
                Study study = Study.create(
                        snowflake.nextId(),
                        "title " + i,
                        "description " + i,
                        5,
                        true,
                        1L,
                        1L
                );

                entityManager.persist(study);
            }
        });
    }
}
