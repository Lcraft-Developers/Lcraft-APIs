package de.lcraft.lapi.javaUtils.api.queueWorker;

import java.util.UUID;
import java.util.function.Consumer;

public interface Process<T> {

    UUID getProcessID();
    Consumer<T> getConsumer();

}
