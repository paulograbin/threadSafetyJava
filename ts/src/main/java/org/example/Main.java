package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        Instant start = Instant.now();

        var counter = new SimpleCounter();
//        var counter = new SynchronizedCounter();
//        var counter = new LockCounter();
//        var counter = new AtomicCounter();

        ExecutorService executorService = Executors.newFixedThreadPool(10000);
        Runnable runnable = counter::increment;

        List<Future> futures = new ArrayList<>();


        for (int i = 0; i <= 10000000; i++) {
            Future<?> future = executorService.submit(runnable);
            futures.add(future);
        }

        for (Future future : futures) {
            if (future.isCancelled()) {
                System.out.println("canceled...");
            } else {
                try {
                    future.get();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println(counter.get());
        System.out.println("It took " + Duration.between(start, Instant.now()).toMillis() + " ms");
    }
}