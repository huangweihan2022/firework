package org.firework.event;

import org.firework.entity.OrderCarrier;
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
