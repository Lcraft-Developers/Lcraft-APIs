package de.lcraft.lapi.javaUtils.api.queueWorker;

public interface Consumer<T> extends QueueWorker<T> {

    void consume();

}
