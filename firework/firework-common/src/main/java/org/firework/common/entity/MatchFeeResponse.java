package org.firework.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 计算报价响应体
 */
@Data
public class MatchFeeResponse implements Serializable {

    /* 匹配过程 */
    private List<String> traces;

    private Settle settle;

    private String message;

    private MatchFeeResponse(){}

    public static MatchFeeResponse ok(List<String> traces, String message, Settle settle){
        MatchFeeResponse matchFeeResponse = new MatchFeeResponse();
        matchFeeResponse.setSettle(settle);
        matchFeeResponse.setMessage(message);
        matchFeeResponse.setTraces(traces);
        return matchFeeResponse;
    }

    public static MatchFeeResponse ok(List<String> traces, Settle settle){
        MatchFeeResponse matchFeeResponse = new MatchFeeResponse();
        matchFeeResponse.setSettle(settle);
        matchFeeResponse.setTraces(traces);
        return matchFeeResponse;
    }

    public static MatchFeeResponse fail(List<String> traces){
        MatchFeeResponse matchFeeResponse = new MatchFeeResponse();
        matchFeeResponse.setSettle(null);
        matchFeeResponse.setTraces(traces);
        return matchFeeResponse;
    }

    public static MatchFeeResponse fail(String message){
        MatchFeeResponse matchFeeResponse = new MatchFeeResponse();
        matchFeeResponse.setSettle(null);
        matchFeeResponse.setMessage(message);
        return matchFeeResponse;
    }
}
