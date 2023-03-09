package org.firework.common.strategy;

import org.firework.common.entity.QuoteFilter;

public interface ISettleFilterStrategy {

    QuoteFilter match(QuoteFilter quoteFilter);
}
