package org.firework.api;

import org.firework.common.entity.CalFeeRequest;
import org.firework.common.entity.MatchFeeResponse;

/**
 * 费用计算的饿dubbo api
 */
public interface ICalSettleRpcService {

    MatchFeeResponse calSettle(CalFeeRequest request);
}
