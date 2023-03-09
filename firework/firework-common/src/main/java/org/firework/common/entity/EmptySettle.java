package org.firework.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 报价｜折扣实体类
 */
public class EmptySettle extends Settle implements Serializable {

    private Long id;

    /* 重量区间起始 */
    private BigDecimal startWeight = BigDecimal.ZERO;

    /* 重量区间终止 */
    private BigDecimal endWeight = BigDecimal.ZERO;

    /* 首重重量 */
    private BigDecimal firstWeight = BigDecimal.ZERO;

    /* 首重价格 */
    private BigDecimal firstPrice = BigDecimal.ZERO;

    /* 续重价格-单位价格 */
    private BigDecimal continuePriceUnit = BigDecimal.ZERO;

    /* 首重折扣 */
    private BigDecimal firstDiscount = BigDecimal.ONE;

    /* 续重折扣 */
    private BigDecimal continueDiscount = BigDecimal.ONE;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public BigDecimal getStartWeight() {
        return startWeight;
    }

    @Override
    public BigDecimal getEndWeight() {
        return endWeight;
    }

    @Override
    public BigDecimal getFirstWeight() {
        return firstWeight;
    }

    @Override
    public BigDecimal getFirstPrice() {
        return firstPrice;
    }

    @Override
    public BigDecimal getContinuePriceUnit() {
        return continuePriceUnit;
    }

    @Override
    public BigDecimal getFirstDiscount() {
        return firstDiscount;
    }

    @Override
    public BigDecimal getContinueDiscount() {
        return continueDiscount;
    }
}
