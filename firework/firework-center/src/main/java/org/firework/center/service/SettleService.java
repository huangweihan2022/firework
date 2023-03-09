package org.firework.center.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.firework.api.ICalSettleRpcService;
import org.firework.common.constant.SettleType;
import org.firework.common.entity.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 费用计算业务
 */
@Service
@Slf4j
public class SettleService {

    @DubboReference
    private ICalSettleRpcService calSettleService;

    /**
     * 费用计算
     */
    public CalFeeResponse calSettle(CalFeeRequest request) {
        String settleType = request.getSettleType();
        // 首先获取报价
        request.setSettleType(SettleType.QUOTE);
        MatchFeeResponse quote = getSettleConfig(request);
        Settle quoteSettle = quote.getSettle();
        if (quoteSettle == null) {
            return CalFeeResponse.fail("do invoke fee fail caused by not find quote config");
        }
        MatchFeeResponse discount = null;
        if (SettleType.DISCOUNT.equals(settleType)) {
            request.setSettleType(settleType);
            discount = getSettleConfig(request);
        }
        return doCalSettle(quote, discount, request.getCalWeight());
    }

    private CalFeeResponse doCalSettle(MatchFeeResponse quote, MatchFeeResponse discount, BigDecimal calWeight) {
        Settle discountSettle = (discount == null) ? null : discount.getSettle();
        if (discountSettle == null) {
            discountSettle = new EmptySettle();
        }
        Settle quoteSettle = quote.getSettle();
        BigDecimal result = quoteSettle.getFirstPrice().multiply(discountSettle.getFirstDiscount());
        BigDecimal firstWeight = quoteSettle.getFirstWeight();
        if (calWeight.compareTo(firstWeight) > 0) {
            BigDecimal continuePrice = (calWeight.subtract(firstWeight)).multiply(discountSettle.getContinueDiscount()).multiply(quoteSettle.getContinuePriceUnit());
            result = result.add(continuePrice);
        }
        return CalFeeResponse.ok(result, quoteSettle.getId(), discountSettle.getId(), quote.getTraces(), (discount == null) ? new ArrayList<>() : discount.getTraces());
    }

    private MatchFeeResponse getSettleConfig(CalFeeRequest request){
        RpcContext.getContext().setAttachment("topic", request.getSettleType());
        MatchFeeResponse matchFeeResponse = calSettleService.calSettle(request);
        log.info("match config result {}", JSON.toJSONString(matchFeeResponse));
        return matchFeeResponse;
    }
}
