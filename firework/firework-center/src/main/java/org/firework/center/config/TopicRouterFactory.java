package org.firework.center.config;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.cluster.Router;
import org.apache.dubbo.rpc.cluster.RouterFactory;

@Activate
public class TopicRouterFactory implements RouterFactory {

    @Override
    public Router getRouter(URL url) {
        return new TopicRouterSelector();
    }
}