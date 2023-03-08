package org.firework.crud.event;

import org.firework.common.entity.OrderCarrier;
import org.springframework.context.ApplicationEvent;

/**
 * @see QuoteChangeListener
 */
public class QuoteChangeEvent extends ApplicationEvent {

    public QuoteChangeEvent(Object source) {
        super(source);
    }

    @Override
    public OrderCarrier getSource() {
        return (OrderCarrier) super.getSource();
    }
}
