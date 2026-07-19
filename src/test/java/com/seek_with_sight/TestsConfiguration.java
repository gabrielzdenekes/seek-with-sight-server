package com.seek_with_sight;

import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.time.Duration;

@TestConfiguration(proxyBeanMethods = false)
@ImportTestcontainers(TestsConfiguration.class)
public class TestsConfiguration {
    @ServiceConnection
    protected static final ElasticsearchContainer elasticsearchContainer =
            new ElasticsearchContainer(DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch:9.4.3"))
                    .withEnv("xpack.security.enabled", "false")
                    .withEnv("ES_JAVA_OPTS", "-Xms256m -Xmx256m")
                    .withEnv("discovery.type", "single-node")
                    .withStartupTimeout(Duration.ofMinutes(10))
                    .withReuse(true);

    @ServiceConnection
    public static final PostgreSQLContainer postgresContainer =
            new PostgreSQLContainer(DockerImageName.parse("postgres:18"))
                    .withReuse(true);

    @ServiceConnection(name = "redis")
    public static GenericContainer<?> redisContainer =
            new GenericContainer<>(DockerImageName.parse("redis:8.2"))
                    .withExposedPorts(6379)
                    .withReuse(true);

    static {
        elasticsearchContainer.start();
        postgresContainer.start();
        redisContainer.start();
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
