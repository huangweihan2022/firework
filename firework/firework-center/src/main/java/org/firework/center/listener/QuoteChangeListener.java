package org.firework.center.listener;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.firework.api.ISettleManageRpcService;
import org.firework.common.constant.SettleType;
import org.firework.common.entity.OrderCarrier;
import org.firework.crud.event.QuoteChangeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 缓存监听器
 */
@Component
public class QuoteChangeListener {

    @DubboReference
    private ISettleManageRpcService settleManageRpcService;

    // 缓存更新
    @EventListener
    public void invoker(QuoteChangeEvent event){
        OrderCarrier orderCarrier = event.getSource();
        RpcContext.getContext().setAttachment("topic", SettleType.QUOTE);
        settleManageRpcService.updateCache(orderCarrier);
    }
}
