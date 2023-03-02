package org.firework.common.entity;

import lombok.Data;
import org.firework.common.constant.OrderType;

/**
 * 事件载体
 */
@Data
public class OrderCarrier {

    /* 载体数据 */
    private Object data;

    /* 命令类型 */
    private OrderType orderType;
}
