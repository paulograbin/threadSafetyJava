package org.example;

import java.util.concurrent.locks.ReentrantLock;

public final class LockCounter implements Counter {

    private int count = 0;


    private final ReentrantLock lock = new ReentrantLock();


    @Override
    public void increment() {
        lock.lock();

        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int get() {
        return count;
    }
}
