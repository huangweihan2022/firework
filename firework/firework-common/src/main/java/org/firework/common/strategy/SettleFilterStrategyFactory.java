package org.firework.common.strategy;

import org.firework.common.entity.QuoteFilter;

import java.util.Arrays;
import java.util.List;

public class SettleFilterStrategyFactory {

    private static final List<ISettleFilterStrategy> filters = Arrays.asList(new SendSiteFilterStrategy(),
            new ReceiveSiteFilterStrategy(), new WeightFilterStrategy());

    public static QuoteFilter filter(QuoteFilter quoteFilter) {
        for (ISettleFilterStrategy filter : filters) {
            quoteFilter = filter.match(quoteFilter);
        }
        return quoteFilter;
    }
}
