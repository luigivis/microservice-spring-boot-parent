package com.luigivismara.microservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {
    private static final Logger log = LoggerFactory.getLogger(AsyncConfig.class);

    @Bean(name = {"daemon", "async"})
    public Executor asyncExecutor() {
        final var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Daemon Thread - ");
        executor.initialize();
        log.info("Daemon thread pool initialized {}{}", executor.getThreadNamePrefix(), executor.getThreadGroup());
        return executor;
    }
}
