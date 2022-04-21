package com.example.demo.config;

import io.debezium.engine.DebeziumEngine;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Data
@Component
public class DebeziumServerBootstrap implements InitializingBean, SmartLifecycle {

    private final Executor executor = Executors.newSingleThreadExecutor();

    @Autowired
    private DebeziumEngine<?> debeziumEngine;

    @Override
    public void start() {
        executor.execute(debeziumEngine);
    }

    @SneakyThrows
    @Override
    public void stop() {
        debeziumEngine.close();
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(debeziumEngine, "debeziumEngine must not be null");
    }




}
