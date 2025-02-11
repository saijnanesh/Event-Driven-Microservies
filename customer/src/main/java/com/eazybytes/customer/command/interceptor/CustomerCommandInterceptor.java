package com.eazybytes.customer.command.interceptor;

import com.eazybytes.customer.command.*;
import com.eazybytes.customer.entity.*;
import com.eazybytes.customer.exception.*;
import com.eazybytes.customer.repository.*;
import lombok.*;
import org.axonframework.commandhandling.*;
import org.axonframework.messaging.*;
import org.springframework.stereotype.*;

import javax.annotation.*;
import java.util.*;
import java.util.function.*;

@Component
@RequiredArgsConstructor
public class CustomerCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {


    private final CustomerRepository repository;

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {
            if (CreateCustomerCommand.class.equals(command.getPayloadType())) {
                CreateCustomerCommand customerCommand = (CreateCustomerCommand) command.getPayload();
                Optional<Customer> byMobileNumberAndActiveSw = repository.findByMobileNumberAndActiveSw(customerCommand.getMobileNumber(), true);
                if (byMobileNumberAndActiveSw.isPresent()) {
                    throw new CustomerAlreadyExistsException("Customer Exists with given Mobile Number");
                }
            } else if (UpdateCustomerCommand.class.equals(command.getPayloadType())) {
                UpdateCustomerCommand updateCustomerCommand = (UpdateCustomerCommand) command.getPayload();
                Customer byMobileNumberAndActiveSw = repository.findByMobileNumberAndActiveSw
                                (updateCustomerCommand.getMobileNumber(), true)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", updateCustomerCommand.getMobileNumber()));
            } else if (DeleteCustomerCommand.class.equals(command.getPayloadType())) {
                DeleteCustomerCommand deleteCustomerCommand = (DeleteCustomerCommand) command.getPayload();
                Customer byMobileNumberAndActiveSw = repository.findByCustomerIdAndActiveSw
                                (deleteCustomerCommand.getCustomerId(), true)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", deleteCustomerCommand.getCustomerId()));
            }
            return command;
        };
    }
}
