package org.firework.quote.event;

import org.firework.common.entity.OrderCarrier;
import org.springframework.context.ApplicationEvent;

/**
 * @see UserChangeListener
 */
public class UserChangeEvent extends ApplicationEvent {

    public UserChangeEvent(Object source) {
        super(source);
    }

    @Override
    public OrderCarrier getSource() {
        return (OrderCarrier) super.getSource();
    }
}
