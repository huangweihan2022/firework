package org.firework.center.controller;

import org.firework.common.constant.HandleStatus;
import org.firework.common.entity.ResultBody;
import org.firework.common.entity.Settle;
import org.firework.crud.service.SettleManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理报价CRUD
 */
@RestController
@RequestMapping("/settle")
public class SettleManageController {

    @Autowired
    private SettleManageService settleManageService;

    /**
     * 查询
     */
    @GetMapping("/query")
    public ResultBody<?> query(){
        List<Settle> settles = settleManageService.query();
        return ResultBody.ok(settles);
    }


    /**
     * 新增
     */
    @PostMapping("/add")
    public ResultBody<?> add(@RequestBody Settle settle){
        String handleStatus = settleManageService.add(settle);
        return HandleStatus.SUCCESS.equals(handleStatus) ? ResultBody.ok() : ResultBody.fail();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public ResultBody<?> delete(@RequestBody Settle settle){
        String handleStatus = settleManageService.delete(settle.getId());
        return HandleStatus.SUCCESS.equals(handleStatus) ? ResultBody.ok() : ResultBody.fail();
    }
}
