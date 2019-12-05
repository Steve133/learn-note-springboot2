package cn.center.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisService<HK, V>{

	// 在构造器中获取redisTemplate实例, key(not hashKey) 默认使用String类型
	@Autowired
    private RedisTemplate<String, V> redisTemplate;
    // 在构造器中通过redisTemplate的工厂方法实例化操作对象
    private HashOperations<String, HK, V> hashOperations;
    private ListOperations<String, V> listOperations;
    private ZSetOperations<String, V> zSetOperations;
    private SetOperations<String, V> setOperations;
    private ValueOperations<String, V> valueOperations;

    public RedisService(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.listOperations = redisTemplate.opsForList();
        this.zSetOperations = redisTemplate.opsForZSet();
        this.setOperations = redisTemplate.opsForSet();
        this.valueOperations = redisTemplate.opsForValue();
    }


    public void hashPut(String key, HK hashKey, V value) {
        hashOperations.put(key, hashKey, value);
    }

    public Map<HK, V> hashFindAll(String key) {
        return hashOperations.entries(key);
    }

    public V hashGet(String key, HK hashKey) {
        return hashOperations.get(key, hashKey);
    }

    public void hashRemove(String key, HK hashKey) {
        hashOperations.delete(key, hashKey);
    }

    public Long listPush(String key, V value) {
        return listOperations.rightPush(key, value);
    }

    public Long listUnshift(String key, V value) {
        return listOperations.leftPush(key, value);
    }

    public List<V> listFindAll(String key) {
        if (!redisTemplate.hasKey(key)) {
            return null;
        }
        return listOperations.range(key, 0, listOperations.size(key));
    }

    public V listLPop(String key) {
        return listOperations.leftPop(key);
    }

    public void setValue(String key, V value) {
        valueOperations.set(key, value);
    }

    /**
     * @description: 设置值
     * @param key
     * @param value
     * @param timeout 秒
     * @author song
     * @date 2019年11月26日 下午9:16:09
     */
    public void setValue(String key, V value, long timeout) {
        ValueOperations<String, V> vo = redisTemplate.opsForValue();
        vo.set(key, value, timeout, TimeUnit.MINUTES);
    }

    /**
     * @description: 设置值
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit 时间类型（不要用毫秒一下时间级（包括毫秒））
     * @author song
     * @date 2019年11月26日 下午9:18:53
     */
    public void setValue(String key, V value, long timeout, TimeUnit timeUnit) {
        ValueOperations<String, V> vo = redisTemplate.opsForValue();
        vo.set(key, value, timeout, timeUnit);
    }

    public V getValue(String key) {
        return valueOperations.get(key);
    }

    public void remove(String key) {
        redisTemplate.delete(key);
    }

    public boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }
}
