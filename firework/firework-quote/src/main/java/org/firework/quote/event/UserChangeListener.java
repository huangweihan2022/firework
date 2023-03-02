package org.firework.quote.event;

import org.firework.common.constant.OrderType;
import org.firework.common.entity.OrderCarrier;
import org.firework.common.entity.User;
import org.firework.quote.cache.UserCacheFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserChangeListener {

    @Autowired
    private UserCacheFactory userCacheFactory;

    @EventListener
    public void invoker(UserChangeEvent event){
        OrderCarrier order = event.getSource();
        OrderType orderType = order.getOrderType();
        if (OrderType.INSERT.equals(orderType)) {
            User user = (User) order.getData();
            userCacheFactory.add(user.getId(), user);
        } else if (OrderType.DELETE.equals(orderType)) {
            userCacheFactory.remove((Integer) order.getData());
        }
    }
}
