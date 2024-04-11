package de.lcraft.lapi.javaUtils.queueWorker;

import de.lcraft.lapi.javaUtils.api.queueWorker.Consumer;
import de.lcraft.lapi.javaUtils.api.queueWorker.ProcessQueue;
import de.lcraft.lapi.javaUtils.api.queueWorker.Producer;

import java.util.ArrayList;
import java.util.List;

public class ProcessQueueManager<T> implements de.lcraft.lapi.javaUtils.api.queueWorker.ProcessQueueManager<T> {

    private ProcessQueue<T> processQueue;
    private List<Producer<T>> producerList;
    private List<Consumer<T>> consumerList;

    public ProcessQueueManager(ProcessQueue<T> processQueue) {
        init(processQueue);
    }
    private void init(ProcessQueue<T> processQueue) {
        if(processQueue != null) setProcessQueue(processQueue);
        setProducerList(new ArrayList<>());
        setConsumerList(new ArrayList<>());
    }

    @Override
    public void startProducer() {
        getProducer().forEach(tProducer -> {
            if(!tProducer.isRunning()) tProducer.run();
        });
    }
    @Override
    public void stopProducer() {
        getProducer().forEach(tProducer -> {
            if(tProducer.isRunning()) tProducer.stop();
        });
    }

    @Override
    public void startConsumer() {
        getConsumer().forEach(tConsumer -> {
            if(!tConsumer.isRunning()) tConsumer.run();
        });
    }
    @Override
    public void stopConsumer() {
        getConsumer().forEach(tConsumer -> {
            if(tConsumer.isRunning()) tConsumer.stop();
        });
    }
    @Override
    public void stopConsumerOnFinish() {
        new Thread(() -> {
            int currentEnabled = 1;
            while(currentEnabled > 0) {
                currentEnabled = 0;
                for(Consumer<T> ignored : getConsumer()) currentEnabled++;
            }
            stopConsumer();
        }).start();
    }

    @Override
    public void addProducer(Producer<T> producer) {
        getProducer().add(producer);
    }
    @Override
    public void addConsumer(Consumer<T> consumer) {
        getConsumer().add(consumer);
    }

    @Override
    public ProcessQueue<T> getProcessQueue() {
        return this.processQueue;
    }
    @Override
    public List<Producer<T>> getProducer() {
        return this.producerList;
    }
    @Override
    public List<Consumer<T>> getConsumer() {
        return this.consumerList;
    }

    private void setProcessQueue(ProcessQueue<T> processQueue) {
        this.processQueue = processQueue;
    }
    private void setProducerList(List<Producer<T>> producerList) {
        this.producerList = producerList;
    }
    private void setConsumerList(List<Consumer<T>> consumerList) {
        this.consumerList = consumerList;
    }

}