package com.eazybytes.customer.command;


import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DeleteCustomerCommand {

    @TargetAggregateIdentifier //similar to @Id annotation in Jpa
    private final String customerId;
    private final boolean activeSw;
}