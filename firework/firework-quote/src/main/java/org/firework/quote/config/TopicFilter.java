package org.firework.quote.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.firework.common.constant.SettleType;

@Slf4j
@Activate
public class TopicFilter implements Filter {

    /**
     * 调用
     *
     * @param invoker    调用程序
     * @param invocation 调用
     * @return {@link Result}
     * @throws RpcException rpc异常
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        invocation.setAttachment("topic", SettleType.QUOTE);
        return invoker.invoke(invocation);
    }

}

