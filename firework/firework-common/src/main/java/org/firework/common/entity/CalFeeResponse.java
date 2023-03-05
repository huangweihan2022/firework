package org.firework.common.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 计算报价响应体
 */
@Data
public class CalFeeResponse {

    /* 计算结果 */
    private BigDecimal result;

    /* 匹配过程 */
    private List<String> traces;

    /* 是否匹配到报价 1是 0否 */
    private Integer code;

    private CalFeeResponse(){}

    public static CalFeeResponse ok(List<String> traces, BigDecimal result){
        CalFeeResponse calFeeResponse = new CalFeeResponse();
        calFeeResponse.setCode(1);
        calFeeResponse.setTraces(traces);
        calFeeResponse.setResult(result);
        return calFeeResponse;
    }

    public static CalFeeResponse fail(List<String> traces){
        CalFeeResponse calFeeResponse = new CalFeeResponse();
        calFeeResponse.setCode(0);
        calFeeResponse.setTraces(traces);
        calFeeResponse.setResult(null);
        return calFeeResponse;
    }
}
