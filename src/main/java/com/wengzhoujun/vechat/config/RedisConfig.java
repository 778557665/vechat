package com.wengzhoujun.vechat.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.wengzhoujun.vechat.common.generator.RedisObjectIdGenerator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisObjectIdGenerator redisIdGenerator(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisStandaloneConfiguration standaloneConfiguration = lettuceConnectionFactory.getStandaloneConfiguration();
        LettuceClientConfiguration clientConfiguration = lettuceConnectionFactory.getClientConfiguration();
        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration(standaloneConfiguration.getHostName(), standaloneConfiguration.getPort());
        serverConfig.setPassword(lettuceConnectionFactory.getStandaloneConfiguration().getPassword());
        serverConfig.setDatabase(4);
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(serverConfig, clientConfiguration);
        connectionFactory.afterPropertiesSet();

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.afterPropertiesSet();
        RedisObjectIdGenerator redisIdGenerator = new RedisObjectIdGenerator();
        redisIdGenerator.setTemplate(redisTemplate);
        return redisIdGenerator;
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认的jdkSerializeable序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisTemplate oneRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisStandaloneConfiguration standaloneConfiguration = lettuceConnectionFactory.getStandaloneConfiguration();
        LettuceClientConfiguration clientConfiguration = lettuceConnectionFactory.getClientConfiguration();
        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration(standaloneConfiguration.getHostName(), standaloneConfiguration.getPort());
        serverConfig.setPassword(lettuceConnectionFactory.getStandaloneConfiguration().getPassword());
        serverConfig.setDatabase(1);
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(serverConfig, clientConfiguration);
        connectionFactory.afterPropertiesSet();

        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManger(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // 设置缓存的默认过期时间，也是使用Duration设置
        config = config.entryTtl(Duration.ofDays(20)).disableCachingNullValues();

        // 设置一个初始化的缓存空间set集合
        Set<String> cacheNames = Sets.newHashSet();
        cacheNames.add("User");

        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>(1);
        configMap.put("User", config.entryTtl(Duration.ofHours(24)));

        // 使用自定义的缓存配置初始化一个cacheManager
        RedisCacheManager cacheManager = RedisCacheManager.builder(connectionFactory)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .build();
        return cacheManager;
    }

}
