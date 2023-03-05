package org.firework.quote.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.firework.common.entity.Settle;

import java.util.List;

@Mapper
public interface IQuoteMapper {

    /**
     * 获取报价配置
     */
    List<Settle> getSettleOfType(String settleType);

    /**
     * 新增报价配置
     */
    Integer addOneSettle(Settle settle);

    /**
     * 删除报价配置
     */
    void deleteSettle(Integer uid);

}
