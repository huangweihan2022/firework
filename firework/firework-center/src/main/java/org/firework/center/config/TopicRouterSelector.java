/**
 * 文件名: CalcRouteSelector.java
 * 版    权: Copyright © 2013 - 2022 ZTO-FREIGHT, Inc. All Rights Reserved
 * 描    述: &lt;描述&gt;
 * 修改人: ROBIN
 * 修改时间: 2022/1/17
 * 跟踪单号: &lt;跟踪单号&gt;
 * 修改单号: &lt;修改单号&gt;
 * 修改内容: &lt;修改内容&gt;
 */
package org.firework.center.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.router.AbstractRouter;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TopicRouterSelector extends AbstractRouter {

    private static final String TOPIC = "topic";

    @Override
    public <T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        String tag = RpcContext.getContext().getAttachment(TOPIC);
        invokers = invokers.stream()
                .filter(p -> tag.equals(p.getUrl().getParameter(TOPIC)))
                .collect(Collectors.toList());
        log.info("dubbo route result {}", JSON.toJSONString(invokers));
        return invokers;
    }
}
