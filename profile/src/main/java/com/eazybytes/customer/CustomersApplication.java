package com.eazybytes.customer;

import com.eazybytes.customer.command.interceptor.*;
import com.netflix.discovery.converters.*;
import org.axonframework.commandhandling.gateway.*;
import org.axonframework.config.*;
import org.axonframework.eventhandling.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CustomersApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomersApplication.class, args);
    }

    @Autowired
    public void registerCustomerCommandInterceptor(ApplicationContext applicationContext, CommandGateway commandGateway) {
        commandGateway.registerDispatchInterceptor(applicationContext.getBean(CustomerCommandInterceptor.class));
    }

    @Autowired
    public void configure(EventProcessingConfigurer eventProcessingConfigurer) {
        eventProcessingConfigurer.registerListenerInvocationErrorHandler("customer-group"
                , conf -> PropagatingErrorHandler.instance());
    }

}
