//package org.firework.quote.config;
//
//import org.apache.dubbo.config.RegistryConfig;
//import org.firework.common.constant.SettleType;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//
///**
// * 自定义注册
// */
//@Component
//public class QuoteZookeeperRegistry {
//
//    @Bean
//    public RegistryConfig registryConfig(){
//        RegistryConfig registryConfig = new RegistryConfig("zookeeper://101.43.3.46:2181");
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("topic", SettleType.QUOTE);
//        registryConfig.setParameters(hashMap);
//        return registryConfig;
//    }
//}
