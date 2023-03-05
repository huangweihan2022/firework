package org.firework.quote.strategy;

import org.apache.dubbo.common.extension.SPI;
import org.firework.common.entity.QuoteFilter;

@SPI
public interface IQuoteFilterStrategy {

    QuoteFilter match(QuoteFilter quoteFilter);
}
