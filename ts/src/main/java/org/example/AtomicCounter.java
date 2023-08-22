package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public final class AtomicCounter implements Counter {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void increment() {
        count.incrementAndGet();
    }

    @Override
    public int get() {
        return count.get();
    }
}
