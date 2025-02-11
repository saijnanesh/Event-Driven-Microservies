package com.eazybytes.customer.command.aggregate;

import com.eazybytes.customer.command.*;
import com.eazybytes.customer.command.event.*;
import com.eazybytes.customer.repository.*;
import org.axonframework.commandhandling.*;
import org.axonframework.eventsourcing.*;
import org.axonframework.modelling.command.*;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.*;

/*
 * we are telling to Axon that this class will act as aggregator and is responsible to
 * process all the commands that we are going to trigger
 * */
@Aggregate
public class CustomerAggregate {

    @AggregateIdentifier
    private String customerId;
    private String name;
    private String email;
    private String mobileNumber;
    private boolean activeSw;

    public CustomerAggregate() {

    }

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand customerCommand, CustomerRepository repository) {
        CustomerCreatedEvent event = new CustomerCreatedEvent();
        BeanUtils.copyProperties(customerCommand, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent customerCreatedEvent) {
        this.customerId = customerCreatedEvent.getCustomerId();
        this.name = customerCreatedEvent.getName();
        this.mobileNumber = customerCreatedEvent.getMobileNumber();
        this.email = customerCreatedEvent.getEmail();
        this.activeSw = customerCreatedEvent.isActiveSw();
    }

    @CommandHandler
    public void command(UpdateCustomerCommand updateCustomerCommand) {
        CustomerUpdatedEvent event = new CustomerUpdatedEvent();
        BeanUtils.copyProperties(updateCustomerCommand, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CustomerUpdatedEvent customerUpdatedEvent) {
        this.name = customerUpdatedEvent.getName();
        this.email = customerUpdatedEvent.getEmail();
    }

    @CommandHandler
    public void command(DeleteCustomerCommand deleteCustomerCommand) {
        CustomerDeletedEvent event = new CustomerDeletedEvent();
        BeanUtils.copyProperties(deleteCustomerCommand, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CustomerDeletedEvent customerDeletedEvent) {
        this.activeSw = customerDeletedEvent.isActiveSw();
    }
}
