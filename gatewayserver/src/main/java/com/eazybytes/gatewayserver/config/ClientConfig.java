package com.eazybytes.gatewayserver.config;

import com.eazybytes.gatewayserver.service.client.CustomerSummaryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {

    @Value("${app.base-url}")
    private String baseUrl;

    @Bean
    CustomerSummaryClient customerClient() {
        WebClient web = WebClient.builder().baseUrl(baseUrl).build();
        WebClientAdapter adapter = WebClientAdapter.create(web);
        HttpServiceProxyFactory procxy = HttpServiceProxyFactory.builderFor(adapter).build();
        return procxy.createClient(CustomerSummaryClient.class);
    }
}
