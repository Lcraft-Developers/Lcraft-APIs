package de.lcraft.lapi.javaUtils.queueWorker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;

public class ProcessQueue<T> implements de.lcraft.lapi.javaUtils.api.queueWorker.ProcessQueue<T> {

    private BlockingQueue<de.lcraft.lapi.javaUtils.api.queueWorker.Process<T>> rawProcessQueue;
    private Integer maxOverflowSize;

    public ProcessQueue(BlockingQueue<de.lcraft.lapi.javaUtils.api.queueWorker.Process<T>> rawProcessQueue, Integer maxOverflowSize) {
        init(rawProcessQueue, maxOverflowSize);
    }
    public ProcessQueue(Integer maxOverflowSize) {
        init(null, maxOverflowSize);
    }
    private void init(BlockingQueue<de.lcraft.lapi.javaUtils.api.queueWorker.Process<T>> rawProcessQueue, Integer maxOverflowSize) {
        if(maxOverflowSize != null) setMaxOverflowSize(maxOverflowSize);
        else setMaxOverflowSize(4096);

        if(rawProcessQueue != null) setRawProcessQueue(rawProcessQueue);
        else setRawProcessQueue(new LinkedBlockingDeque<>(getMaxOverflowSize()));
    }

    @Override
    public void add(de.lcraft.lapi.javaUtils.api.queueWorker.Process<T> process) {
        getRawProcessQueue().add(process);
    }
    @Override
    public void add(Consumer<T> consumer) {
        add(new Process<>(consumer));
    }
    @Override
    public de.lcraft.lapi.javaUtils.api.queueWorker.Process<T> getAndRemoveCurrentProcess() throws InterruptedException {
        return getRawProcessQueue().take();
    }

    @Override
    public BlockingQueue<de.lcraft.lapi.javaUtils.api.queueWorker.Process<T>> getRawProcessQueue() {
        return this.rawProcessQueue;
    }
    @Override
    public Integer getMaxOverflowSize() {
        return this.maxOverflowSize;
    }

    private void setRawProcessQueue(BlockingQueue<de.lcraft.lapi.javaUtils.api.queueWorker.Process<T>> rawProcessQueue) {
        this.rawProcessQueue = rawProcessQueue;
    }
    private void setMaxOverflowSize(Integer maxOverflowSize) {
        this.maxOverflowSize = maxOverflowSize;
    }

}
