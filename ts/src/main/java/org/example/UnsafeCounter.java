package org.example;

import sun.misc.Unsafe;

import java.util.concurrent.locks.ReentrantLock;

public final class UnsafeCounter implements Counter {

    private volatile int count = 0;
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset(UnsafeCounter.class.getDeclaredField("count"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public void increment() {
        int current;

        do {
            current = unsafe.getIntVolatile(this, valueOffset);
        } while (!unsafe.compareAndSwapInt(this, valueOffset, current, current + 1));
    }

    @Override
    public int get() {
        return count;
    }
}
