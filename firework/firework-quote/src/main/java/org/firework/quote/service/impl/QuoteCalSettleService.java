package org.firework.quote.service.impl;

import org.firework.api.ICalSettleService;
import org.firework.common.entity.CalFeeRequest;
import org.firework.common.entity.CalFeeResponse;
import org.firework.common.entity.QuoteFilter;
import org.firework.common.entity.Settle;
import org.firework.quote.cache.QuoteCacheFactory;
import org.firework.quote.strategy.IQuoteFilterStrategy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户-业务层
 */
@Service
public class QuoteCalSettleService implements ICalSettleService, ApplicationContextAware {

    @Autowired
    private QuoteCacheFactory quoteCacheFactory;

    @Autowired
    private List<IQuoteFilterStrategy> quoteFilterStrategies;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

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
        if (calWeight.compareTo(settle.getFistWeight()) > 0) {
            BigDecimal continuePrice = calWeight.subtract(settle.getFistWeight()).multiply(settle.getContinuePriceUnit());
            result = result.add(continuePrice);
        }
        return CalFeeResponse.ok(quoteFilter.getTraces(), result);
    }
}
