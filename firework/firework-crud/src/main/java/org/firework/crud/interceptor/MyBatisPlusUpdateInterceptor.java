package org.firework.crud.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.firework.common.constant.OrderType;
import org.firework.common.entity.OrderCarrier;
import org.firework.common.entity.Settle;
import org.firework.crud.event.QuoteChangeEvent;
import org.firework.crud.service.SettleManageService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Intercepts({
        @Signature(
                type = Executor.class ,
                method = "update",
                args={MappedStatement.class,Object.class})
})
@Component
@Slf4j
public class MyBatisPlusUpdateInterceptor implements Interceptor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private SettleManageService settleManageService;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 先执行sql
        Object proceed = invocation.proceed();
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object[] args = invocation.getArgs();
        // 获取方法入参；ps：技术优先，新增先别搞事情，单一个参数就行
        Object parameter = args[1];
        Configuration configuration = mappedStatement.getConfiguration();
        Object target = invocation.getTarget();
        StatementHandler statementHandler = configuration.newStatementHandler((Executor) target, mappedStatement, parameter, RowBounds.DEFAULT, null, null);
        BoundSql boundsql = statementHandler.getBoundSql();
        String sql = boundsql.getSql();
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        OrderCarrier orderCarrier = new OrderCarrier();
        if(sqlCommandType.equals(SqlCommandType.INSERT)){
            // 新增数据主键返回
            Settle settle = (Settle) parameter;
            orderCarrier.setData(settle);
            orderCarrier.setOrderType(OrderType.INSERT);
            orderCarrier.setSettleType(settle.getSettleType());
        } else if(sqlCommandType.equals(SqlCommandType.DELETE)){
            orderCarrier.setData(parameter);
            Settle settle = settleManageService.selectOne((Long)parameter);
            orderCarrier.setOrderType(OrderType.DELETE);
            orderCarrier.setSettleType(settle.getSettleType());
        } else {
            // 暂时不考虑
            orderCarrier.setOrderType(OrderType.UPDATE);
        }

        String name = orderCarrier.getOrderType().name();
        log.info("执行sql：{}={}", name, sql);
        applicationContext.publishEvent(new QuoteChangeEvent(orderCarrier));
        return proceed;
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
