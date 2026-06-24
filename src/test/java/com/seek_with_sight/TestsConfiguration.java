package com.seek_with_sight;

import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

@TestConfiguration(proxyBeanMethods = false)
public class TestsConfiguration {

    @Bean
    @ServiceConnection
    public PostgreSQLContainer postgresContainer() {
        return new PostgreSQLContainer(DockerImageName.parse("postgres:18"))
                .withReuse(true);
    }

    @Bean
    @ServiceConnection(name = "redis")
    public GenericContainer<?> redisContainer() {
        return new GenericContainer<>(DockerImageName.parse("redis:8.2"))
                .withExposedPorts(6379)
                .withReuse(true);
    }

    @Configuration
    static class DataSourcePostProcessorConfiguration {
        @Bean
        public static BeanPostProcessor dataSourceWrapper() {
            return new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) {
                    if (bean instanceof DataSource originalDataSource) {
                        return ProxyDataSourceBuilder.create(originalDataSource)
                                .name("Hypersistence-Proxy")
                                .countQuery()
                                .logQueryToSysOut()
                                .build();
                    }
                    return bean;
                }
            };
        }
    }
}
