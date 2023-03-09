package org.firework.discount.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.firework.api.ISettleManageRpcService;
import org.firework.common.constant.OrderType;
import org.firework.common.entity.OrderCarrier;
import org.firework.common.entity.Settle;
import org.firework.discount.cache.DiscountCacheFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 更新缓存RPC
 */
@Slf4j
public class SettleManageRpcService implements ISettleManageRpcService {

    @Autowired
    private DiscountCacheFactory cacheFactory;

    @Override
    public void updateCache(OrderCarrier orderCarrier) {
        log.info("quote get update cache request param {}", JSON.toJSONString(orderCarrier));
        OrderType orderType = orderCarrier.getOrderType();
        switch (orderType) {
            case INSERT:
                Settle settle = (Settle) orderCarrier.getData();
                cacheFactory.add(settle.getId(), settle); break;
            case DELETE:
                Long id = (Long) orderCarrier.getData();
                cacheFactory.remove(id); break;
            case UPDATE:
                break;
            default:
                throw new RuntimeException("未知命令类型：" + orderType);
        }
    }
}
