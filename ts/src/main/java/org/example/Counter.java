package org.example;

public sealed interface Counter permits AtomicCounter, LockCounter, SimpleCounter, SynchronizedCounter, UnsafeCounter, VarHandleCounter {

    void increment();

    int get();

}
