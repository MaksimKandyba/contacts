package contacts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootTest
@ActiveProfiles("memory-consumption-test")
public class ContactsControllerMemoryConsumptionTest {

    @Autowired
    private ContactsController controller;

    @Test
    public void test() throws InterruptedException, ExecutionException {
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        ExecutorService service = null;
        final int threadAmount = 100;
        Future<?>[] responses;
        final int requestAmount = 1000;
        try {
            service = Executors.newFixedThreadPool(threadAmount);
            responses = new Future[requestAmount];
            for (int i = 0; i < requestAmount; i++) {
                responses[i] = service.submit(() -> controller.get("^[A-Z].*$"));
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
        for (Future<?> response : responses) {
            response.get();
        }
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long increase = memoryAfter - memoryBefore;
        System.out.println("memory consumption for "
                + threadAmount
                + " thread(s) making "
                + requestAmount
                + " request(s): "
                + increase
                + " byte(s) ~= "
                + increase / 1024
                + " KB ~= "
                + increase / 1024 / 1024
                + " MB");
    }
}
