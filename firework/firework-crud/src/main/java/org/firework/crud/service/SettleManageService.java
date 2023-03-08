package org.firework.crud.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.firework.common.constant.HandleStatus;
import org.firework.common.entity.Settle;
import org.firework.crud.mapper.ISettleManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * 费用计算业务
 */
@Service
@Slf4j
public class SettleManageService {

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private ISettleManageMapper settleManageMapper;

    /**
     * 查询
     */
    public List<Settle> query() {
        return this.settleManageMapper.query();
    }


    /**
     * 新增
     */
    public String add(Settle settle) {
        log.info("add settle param {}", JSON.toJSONString(settle));
        TransactionStatus transactionStatus = null;
        try {
            transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
            this.settleManageMapper.add(settle);
            transactionManager.commit(transactionStatus);
            return HandleStatus.SUCCESS;
        } catch (Exception ex) {
            if (transactionStatus != null) {
                transactionManager.rollback(transactionStatus);
            }
            log.error("add settle param error {} - {}", JSON.toJSONString(settle), ex.getMessage());
            return HandleStatus.FAIL;
        }
    }

    /**
     * 删除
     */
    public String delete(Long id) {
        log.info("add settle param {}", id);
        TransactionStatus transactionStatus = null;
        try {
            transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
            this.settleManageMapper.delete(id);
            transactionManager.commit(transactionStatus);
            return HandleStatus.SUCCESS;
        } catch (Exception ex) {
            if (transactionStatus != null) {
                transactionManager.rollback(transactionStatus);
            }
            log.error("add settle param error {} - {}", id, ex.getMessage());
            return HandleStatus.FAIL;
        }
    }
}
