package de.lcraft.lapi.javaUtils.api.queueWorker;

import java.util.List;

public interface ProcessQueueManager<T> {

    void startProducer();
    void stopProducer();

    void startConsumer();
    void stopConsumer();
    void stopConsumerOnFinish();

    void addProducer(Producer<T> producer);
    void addConsumer(Consumer<T> consumer);

    ProcessQueue<T> getProcessQueue();
    List<Producer<T>> getProducer();
    List<Consumer<T>> getConsumer();

}
