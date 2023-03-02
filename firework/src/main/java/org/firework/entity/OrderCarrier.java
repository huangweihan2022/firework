package org.firework.entity;

import lombok.Data;
import org.firework.common.OrderType;

/**
 * 事件载体
 */
@Data
public class OrderCarrier {

    private Object data;

    private OrderType orderType;
}
