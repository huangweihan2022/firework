package org.firework.quote.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.firework.api.ICalSettleRpcService;
import org.firework.common.entity.CalFeeRequest;
import org.firework.common.entity.CalFeeResponse;
import org.firework.common.entity.QuoteFilter;
import org.firework.common.entity.Settle;
import org.firework.quote.cache.QuoteCacheFactory;
import org.firework.quote.strategy.IQuoteFilterStrategy;
import org.firework.quote.strategy.ReceiveSiteFilterStrategy;
import org.firework.quote.strategy.SendSiteFilterStrategy;
import org.firework.quote.strategy.WeightFilterStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 用户-业务层
 */
@Component
@Slf4j
@DubboService
public class QuoteCalSettleRpcService implements ICalSettleRpcService{

    @Autowired
    private QuoteCacheFactory quoteCacheFactory;

    private static final List<IQuoteFilterStrategy> quoteFilterStrategies = Arrays.asList(new SendSiteFilterStrategy(),
            new ReceiveSiteFilterStrategy(), new WeightFilterStrategy());

    /* 报价计算 */
    @Override
    public CalFeeResponse calSettle(CalFeeRequest request) {

        // 获取计算重量
        BigDecimal calWeight = request.getCalWeight();
        // 报价匹配
        List<Settle> candidates = quoteCacheFactory.getAllSettleConfigOfType();
        QuoteFilter quoteFilter = new QuoteFilter();
        quoteFilter.setCalWeight(calWeight);
        quoteFilter.setLeader(request.getSettle());
        quoteFilter.setCandidates(candidates);
        for (IQuoteFilterStrategy quoteFilterStrategy : quoteFilterStrategies) {
            quoteFilter = quoteFilterStrategy.match(quoteFilter);
        }
        candidates = quoteFilter.getCandidates();
        if (candidates.isEmpty()) {
            return CalFeeResponse.fail(quoteFilter.getTraces());
        }
        Settle settle = candidates.iterator().next();
        // 报价计算
        // 价格=首重价格+（重量-首重）*续重价格
        BigDecimal result = settle.getFirstPrice();
        if (calWeight.compareTo(settle.getFirstWeight()) > 0) {
            BigDecimal continuePrice = calWeight.subtract(settle.getFirstWeight()).multiply(settle.getContinuePriceUnit());
            result = result.add(continuePrice);
        }
        return CalFeeResponse.ok(quoteFilter.getTraces(), result);
    }
}
