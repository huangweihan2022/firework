package org.firework.api;

import org.firework.common.entity.CalFeeRequest;
import org.firework.common.entity.CalFeeResponse;

/**
 * 费用计算的饿dubbo api
 */
public interface ICalSettleRpcService {

    CalFeeResponse calSettle(CalFeeRequest request);
}
