package com.neural.nets.ns_first_lab.redis.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.neural.nets.ns_first_lab.entity.Matrix
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.GenericToStringSerializer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer


@Configuration
@EnableRedisRepositories
class RedisConfiguration {

    @Bean
    fun connectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory()
    }

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, Matrix> {
        val template = RedisTemplate<String, Matrix>()
        template.connectionFactory = connectionFactory
        template.keySerializer = GenericToStringSerializer(String::class.java)
        template.valueSerializer = GenericToStringSerializer(Matrix::class.java)
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        template.hashKeySerializer = GenericJackson2JsonRedisSerializer()
        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()
        return template
    }
}
