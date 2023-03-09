package org.firework.common.entity;

import lombok.Data;
import org.firework.common.constant.OrderType;

import java.io.Serializable;

/**
 * 事件载体
 */
@Data
public class OrderCarrier implements Serializable {

    /* 载体数据 */
    private Object data;

    /* 命令类型 */
    private OrderType orderType;

    /* 费用类型 */
    private String settleType;
}
