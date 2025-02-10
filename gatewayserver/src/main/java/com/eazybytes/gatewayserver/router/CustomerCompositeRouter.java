package com.eazybytes.gatewayserver.router;


import com.eazybytes.gatewayserver.handler.CustomerCompositrHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration(proxyBeanMethods = false)
public class CustomerCompositeRouter {

    @Bean
    public RouterFunction<ServerResponse> route(CustomerCompositrHandler handler) {
        return RouterFunctions.route(RequestPredicates.GET("/api/composite/fetchCustomerSummary")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                .and(RequestPredicates.queryParam("mobileNumber",param -> true)),
                (handler::fetchCustomerSummery));

    }
}
