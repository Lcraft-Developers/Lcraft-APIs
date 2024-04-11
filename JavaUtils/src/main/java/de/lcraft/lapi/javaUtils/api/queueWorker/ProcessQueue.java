package de.lcraft.lapi.javaUtils.api.queueWorker;

import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public interface ProcessQueue<T> {

    void add(Process<T> process);
    void add(Consumer<T> consumer);
    Process<T> getAndRemoveCurrentProcess() throws InterruptedException;

    BlockingQueue<Process<T>> getRawProcessQueue();
    Integer getMaxOverflowSize();

}
