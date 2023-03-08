package org.firework.api;

import org.firework.common.entity.OrderCarrier;

/**
 * 报价更新缓存通知接口
 */
public interface ISettleManageRpcService {

    /* 缓存更新 */
    void updateCache(OrderCarrier orderCarrier);
}
