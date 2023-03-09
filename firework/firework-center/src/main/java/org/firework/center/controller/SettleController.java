package org.firework.center.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.firework.center.service.SettleService;
import org.firework.common.entity.CalFeeRequest;
import org.firework.common.entity.CalFeeResponse;
import org.firework.common.entity.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 费用计算的入口
 */
@Slf4j
@RestController
@RequestMapping("/settle")
public class SettleController {

    @Autowired
    private SettleService settleService;

    /**
     * 费用计算
     */
    @GetMapping("/cal")
    public ResultBody<?> calSettle(@RequestBody CalFeeRequest request){
        try {
            log.info("fee cal param [{}]", JSON.toJSONString(request));
            CalFeeResponse calFeeResponse = this.settleService.calSettle(request);
            log.info("fee cal result [{}]", JSON.toJSONString(calFeeResponse));
            return ResultBody.ok(calFeeResponse);
        } catch (Exception ex) {
            String errorMsg = ex.getMessage();
            log.info("fee cal error [{}]", errorMsg);
            return ResultBody.fail(errorMsg);
        }
    }
}
