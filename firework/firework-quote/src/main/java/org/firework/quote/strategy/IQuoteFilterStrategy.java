package org.firework.quote.strategy;

import org.firework.common.entity.QuoteFilter;

public interface IQuoteFilterStrategy {

    QuoteFilter match(QuoteFilter quoteFilter);
}
