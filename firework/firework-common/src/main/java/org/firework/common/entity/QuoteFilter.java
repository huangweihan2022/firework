package org.firework.common.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class QuoteFilter {

    /* 轨迹 */
    private List<String> traces = new ArrayList<>();

    /* 候选报价 */
    private List<Settle> candidates;

    /* 待匹配报价 */
    private Settle leader;

    /* 计算重量 */
    private BigDecimal calWeight;
}
