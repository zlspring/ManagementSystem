package com.aohui.btcorg

import com.fasterxml.jackson.databind.ObjectMapper
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.codec.JsonJacksonCodec
import org.redisson.config.Config
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
class DataSourceConfig {

    @Bean(destroyMethod = "close")
    @ConfigurationProperties(prefix="test.datasource")
    fun dataSource(): HikariDataSource {
        val config = HikariConfig()
        config.jdbcUrl = "jdbc:mysql://127.0.0.1:3306/backstage?useUnicode=true&serverTimezone=GMT%2b8"
        config.username = "root"
        config.password = "root"
        config.connectionTestQuery = "SELECT 1"
        config.isReadOnly = false
        config.connectionTimeout = 30000
        config.validationTimeout = 10000
        config.validationTimeout = 600000
        config.maximumPoolSize = 25
        config.connectionInitSql = "SELECT 1"
        return HikariDataSource(config)
    }

    val redisPwd = "123456"
    val redisServer="redis://127.0.0.1:6379"

    @Bean(destroyMethod = "shutdown")
    fun redisson(objectMapper: ObjectMapper):RedissonClient{
        val config = Config()
        config.setCodec(JsonJacksonCodec(objectMapper))
                .useSingleServer()
                .setConnectionPoolSize(32)
                .setAddress(redisServer)
                .setPassword(redisPwd)
        return Redisson.create(config)
    }
}