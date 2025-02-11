package com.eazybytes.customer.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateCustomerCommand {

    @TargetAggregateIdentifier //similar to @Id annotation in Jpa
    private final String customerId;
    private final String name;
    private final String email;
    private final String mobileNumber;
    private final boolean activeSw;
}
