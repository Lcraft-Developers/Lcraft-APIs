package de.lcraft.lapi.javaUtils.api.queueWorker;

public interface Producer<T> extends QueueWorker<T> {

    void produce();

}
