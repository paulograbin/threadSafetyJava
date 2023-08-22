package org.example;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public final class VarHandleCounter implements Counter {

    private volatile int count = 0;
    private static final VarHandle VALUE;

    static {
        try {
            VALUE = MethodHandles.lookup().findVarHandle(VarHandleCounter.class, "count", int.class);
        }catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @Override
    public synchronized void increment() {
        int current;

        do {
            current = (int) VALUE.getVolatile(this);
        } while (!VALUE.compareAndSet(this, current, current + 1));

        count++;
    }

    @Override
    public synchronized int get() {
        return count;
    }
}
