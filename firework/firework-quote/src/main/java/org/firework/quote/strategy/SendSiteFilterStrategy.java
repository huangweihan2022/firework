package org.firework.quote.strategy;

import org.apache.dubbo.common.extension.Activate;
import org.firework.common.entity.QuoteFilter;
import org.firework.common.entity.Settle;
import org.springframework.core.Ordered;

import java.util.List;
import java.util.stream.Collectors;

/**
 * this is quote config filter for send site
 */
@Activate
public class SendSiteFilterStrategy implements IQuoteFilterStrategy, Ordered {

    @Override
    public QuoteFilter match(QuoteFilter quoteFilter){
        List<Settle> candidates = quoteFilter.getCandidates();
        StringBuilder trace = new StringBuilder("before match send site, candidates have " + candidates.size());
        if (candidates.isEmpty()) {
            return quoteFilter;
        }
        Settle leader = quoteFilter.getLeader();
        candidates = candidates.stream().filter(p -> p.getSendSiteId().equals(leader.getSendSiteId())).collect(Collectors.toList());
        trace.append("after match send site, candidates have ").append(candidates.size());
        quoteFilter.getTraces().add(trace.toString());
        quoteFilter.setCandidates(candidates);
        return quoteFilter;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
