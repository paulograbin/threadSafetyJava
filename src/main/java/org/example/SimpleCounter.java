package org.example;

public final class SimpleCounter implements Counter {

    private int count = 0;

    @Override
    public void increment() {
        count++;
    }

    @Override
    public int get() {
        return count;
    }
}
