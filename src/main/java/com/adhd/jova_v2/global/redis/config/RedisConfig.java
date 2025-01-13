package com.adhd.jova_v2.global.redis.config;

import com.adhd.jova_v2.global.security.jwt.event.listener.RefreshTokenExpirationListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {

    private final String redisHost;
    private final int redisPort;
    private final RedisConnectionFactory redisConnectionFactory;

    public RedisConfig(
            @Value("${spring.data.redis.host}") String redisHost,
            @Value("${spring.data.redis.port}") int redisPort, RedisConnectionFactory redisConnectionFactory
    ) {
        this.redisHost = redisHost;
        this.redisPort = redisPort;
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .useSsl()
                .and()
                .commandTimeout(Duration.ofSeconds(5))
                .shutdownTimeout(Duration.ofSeconds(2))
                .build();
        return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);
    }

    @Bean
    public RedisTemplate<String, Object> authCodeRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, Object> blackListRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisMessageListenerContainer redisContainer(MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("__keyevent@*__:expired"));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RefreshTokenExpirationListener listener) {
        return new MessageListenerAdapter(listener);
    }

    private GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }
}