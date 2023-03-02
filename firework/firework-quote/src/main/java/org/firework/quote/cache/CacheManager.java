package org.firework.quote.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 模拟缓存
 */
public class CacheManager<K, V> {

    /**
     * 缓存管理器
     */
    private final Map<K, V> CACHE;

    public CacheManager(Map<K, V> cache){
        this.CACHE = cache;
    }

    /**
     * 新增缓存
     */
    public void put(K key, V value) {
        CACHE.put(key, value);
    }

    /**
     * 获取缓存
     */
    public V get(K key){
        return CACHE.get(key);
    }

    /**
     * 删除缓存
     */
    public V remove(K key){
        return CACHE.remove(key);
    }

    public List<V> getAllCache(){
        return new ArrayList<>(CACHE.values());
    }
}
