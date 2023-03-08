package org.firework.common.entity;

import lombok.Data;
import org.firework.common.constant.SettleType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 报价｜折扣实体类
 */
@Data
public class Settle implements Serializable {

    private Long id;

    /* 寄件区域ID */
    private String sendSiteId;

    /* 寄件区域名称 */
    private String sendSiteName;

    /* 派件区域ID */
    private String receiveSiteId;

    /* 派件区域名称 */
    private String receiveSiteName;

    /* 重量区间起始 */
    private BigDecimal startWeight;

    /* 重量区间终止 */
    private BigDecimal endWeight;

    /* 首重重量 */
    private BigDecimal firstWeight;

    /* 首重价格 */
    private BigDecimal firstPrice;

    /* 续重价格-单位价格 */
    private BigDecimal continuePriceUnit;

    /* 首重折扣 */
    private BigDecimal firstDiscount;

    /* 续重折扣 */
    private BigDecimal continueDiscount;

    /**
     * 当前配置类型 报价｜折扣
     * @see SettleType
     */
    private String settleType;

    /* 是否生效 0生效 1失效 */
    private boolean deleted;
}
