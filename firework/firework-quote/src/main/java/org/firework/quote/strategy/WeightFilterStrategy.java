package org.firework.quote.strategy;

import org.apache.dubbo.common.extension.Activate;
import org.firework.common.entity.QuoteFilter;
import org.firework.common.entity.Settle;
import org.springframework.core.Ordered;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * this is quote config filter for weight
 */
@Activate
public class WeightFilterStrategy implements IQuoteFilterStrategy, Ordered {

    @Override
    public QuoteFilter match(QuoteFilter quoteFilter){
        List<Settle> candidates = quoteFilter.getCandidates();
        StringBuilder trace = new StringBuilder("before match weight, candidates have " + candidates.size());
        if (candidates.isEmpty()) {
            return quoteFilter;
        }
        BigDecimal calWeight = quoteFilter.getCalWeight();
        candidates = candidates.stream().filter(p -> {
            BigDecimal startWeight = p.getStartWeight();
            BigDecimal endWeight = p.getEndWeight();
            return startWeight.compareTo(calWeight) > 0 && endWeight.compareTo(calWeight) > 0;
        }).collect(Collectors.toList());
        trace.append("after match weight, candidates have ").append(candidates.size());
        quoteFilter.getTraces().add(trace.toString());
        quoteFilter.setCandidates(candidates);
        return quoteFilter;
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
