package de.lcraft.lapi.javaUtils.queueWorker;

import java.util.UUID;
import java.util.function.Consumer;

public class Process<T> implements de.lcraft.lapi.javaUtils.api.queueWorker.Process<T> {

    private UUID processID;
    private Consumer<T> consumer;

    public Process(Consumer<T> consumer) {
        init(null, consumer);
    }
    private void init(UUID processID, Consumer<T> consumer) {
        if(processID != null) setProcessID(processID);
        else setProcessID(UUID.randomUUID());

        if(consumer != null) setConsumer(consumer);
        else setConsumer(t -> {});
    }

    @Override
    public UUID getProcessID() {
        return this.processID;
    }
    @Override
    public Consumer<T> getConsumer() {
        return this.consumer;
    }

    private void setProcessID(UUID processID) {
        this.processID = processID;
    }
    private void setConsumer(Consumer<T> consumer) {
        this.consumer = consumer;
    }

}
