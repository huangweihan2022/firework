package org.firework.crud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.firework.common.entity.Settle;

import java.util.List;

@Mapper
public interface ISettleManageMapper {

    /**
     * 获取报价配置
     */
    List<Settle> query();

    /**
     * 新增报价配置
     */
    Integer add(Settle settle);

    /**
     * 删除报价配置
     */
    void delete(Long id);

}
