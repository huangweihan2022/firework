package org.firework.quote.event;

import org.firework.common.constant.OrderType;
import org.firework.common.entity.OrderCarrier;
import org.firework.common.entity.Settle;
import org.firework.quote.cache.QuoteCacheFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class QuoteChangeListener {

    @Autowired
    private QuoteCacheFactory quoteCacheFactory;

    @EventListener
    public void invoker(QuoteChangeEvent event){
        OrderCarrier order = event.getSource();
        OrderType orderType = order.getOrderType();
        if (OrderType.INSERT.equals(orderType)) {
            Settle settle = (Settle) order.getData();
            quoteCacheFactory.add(settle.getId(), settle);
        } else if (OrderType.DELETE.equals(orderType)) {
            quoteCacheFactory.remove((Long) order.getData());
        }
    }
}
