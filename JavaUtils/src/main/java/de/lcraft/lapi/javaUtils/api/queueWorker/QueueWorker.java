package de.lcraft.lapi.javaUtils.api.queueWorker;

public interface QueueWorker<T> extends Runnable {

    @Override
    void run();
    void stop();

    void process();

    ProcessQueue<T> getProcessQueue();
    Boolean isRunning();

}
