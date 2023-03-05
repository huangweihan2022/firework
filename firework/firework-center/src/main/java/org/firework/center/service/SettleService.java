package org.firework.center.service;

import org.firework.api.ICalSettleService;
import org.firework.common.entity.CalFeeRequest;
import org.firework.common.entity.CalFeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 费用计算业务
 */
@Service
public class SettleService {

    @Autowired
    private ICalSettleService calSettleService;

    /**
     * 费用计算
     */
    public CalFeeResponse calSettle(CalFeeRequest request) {
        return calSettleService.calSettle(request);
    }
}
