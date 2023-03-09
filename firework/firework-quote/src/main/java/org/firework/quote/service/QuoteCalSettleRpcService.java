package org.firework.quote.service;

import lombok.extern.slf4j.Slf4j;
import org.firework.api.ICalSettleRpcService;
import org.firework.common.entity.CalFeeRequest;
import org.firework.common.entity.MatchFeeResponse;
import org.firework.common.entity.QuoteFilter;
import org.firework.common.entity.Settle;
import org.firework.common.strategy.SettleFilterStrategyFactory;
import org.firework.quote.cache.QuoteCacheFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户-业务层
 */
@Component
@Slf4j
public class QuoteCalSettleRpcService implements ICalSettleRpcService{

    @Autowired
    private QuoteCacheFactory quoteCacheFactory;

    /* 报价计算 */
    @Override
    public MatchFeeResponse calSettle(CalFeeRequest request) {

        // 获取计算重量
        BigDecimal calWeight = request.getCalWeight();
        // 报价匹配
        List<Settle> candidates = quoteCacheFactory.getAllSettleConfigOfType();
        QuoteFilter quoteFilter = new QuoteFilter();
        quoteFilter.setCalWeight(calWeight);
        quoteFilter.setLeader(request.getSettle());
        quoteFilter.setCandidates(candidates);
        quoteFilter = SettleFilterStrategyFactory.filter(quoteFilter);
        candidates = quoteFilter.getCandidates();
        if (candidates.isEmpty()) {
            return MatchFeeResponse.fail(quoteFilter.getTraces());
        }
        Settle settle = candidates.iterator().next();
        return MatchFeeResponse.ok(quoteFilter.getTraces(), settle);
    }
}
