package org.firework.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 计算报价响应体
 */
@Data
public class CalFeeResponse implements Serializable {

    private String message;

    private Long quoteId;

    private Long discountId;

    private BigDecimal result;

    private List<String> quoteTraces;

    private List<String> discountTraces;

    public static CalFeeResponse fail(String message) {
        CalFeeResponse calFeeResponse = new CalFeeResponse();
        calFeeResponse.setMessage(message);
        return calFeeResponse;
    }

    public static CalFeeResponse ok(BigDecimal result, Long quoteId, Long discountId, List<String> quoteTraces, List<String> discountTraces) {
        CalFeeResponse calFeeResponse = new CalFeeResponse();
        calFeeResponse.setResult(result);
        calFeeResponse.setQuoteId(quoteId);
        calFeeResponse.setDiscountId(discountId);
        calFeeResponse.setQuoteTraces(quoteTraces);
        calFeeResponse.setDiscountTraces(discountTraces);
        return calFeeResponse;
    }
}
