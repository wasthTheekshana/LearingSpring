package com.wasathDev.LearingSpring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPoolConfiguration {

    private final Environment environment;

    @Autowired
    public ThreadPoolConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Primary
    @Bean("ThreadPoolExecutor")
    public Executor sinkTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Integer.parseInt(environment.getRequiredProperty("executor.core.pool.size")));
        executor.setMaxPoolSize(Integer.parseInt(environment.getRequiredProperty("executor.max.pool.size")));
        executor.setQueueCapacity(Integer.parseInt(environment.getRequiredProperty("executor.queue.size")));
        executor.setThreadNamePrefix("EXECUTOR-X");
        executor.initialize();
        return executor;
    }

}