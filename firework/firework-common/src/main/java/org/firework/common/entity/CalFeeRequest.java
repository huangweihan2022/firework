package org.firework.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 计算报价请求体
 */
@Data
public class CalFeeRequest implements Serializable {

    /* 报价实体类 */
    private Settle settle;

    /* 计算重量 */
    private BigDecimal calWeight;

    /* 计算费用类型 */
    private String settleType;
}
