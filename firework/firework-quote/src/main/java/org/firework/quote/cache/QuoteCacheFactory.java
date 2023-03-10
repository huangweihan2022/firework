package org.firework.quote.cache;

import org.firework.common.cache.CacheManager;
import org.firework.common.constant.SettleType;
import org.firework.common.entity.Settle;
import org.firework.crud.mapper.ISettleManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 报价缓存
 */
@Component
public class QuoteCacheFactory {

    /* mock local cache */
    private static final CacheManager<Long, Settle> QUOTE_CACHE_MAP = new CacheManager<>(new HashMap<>());

    @Autowired
    private ISettleManageMapper settleManageMapper;

    /**
     * application start up will init order type is quote all cache
     */
    @PostConstruct
    public void init(){
        List<Settle> quoteSettleConfigs = settleManageMapper.query(SettleType.QUOTE);
        quoteSettleConfigs.removeIf(Objects::isNull);
        quoteSettleConfigs.forEach(p -> QUOTE_CACHE_MAP.put(p.getId(), p));
    }

    /**
     * add cache
     */
    public void add(Long key, Settle settle){
        QUOTE_CACHE_MAP.put(key, settle);
    }

    /**
     * remove cache
     */
    public void remove(Long key){
        QUOTE_CACHE_MAP.remove(key);
    }

    /**
     * get cache
     */
    public Settle get(Long key) {
        return QUOTE_CACHE_MAP.get(key);
    }

    /**
     * get all settle config of type
     */
    public List<Settle> getAllSettleConfigOfType(){
        return QUOTE_CACHE_MAP.getAllCache();
    }
}
