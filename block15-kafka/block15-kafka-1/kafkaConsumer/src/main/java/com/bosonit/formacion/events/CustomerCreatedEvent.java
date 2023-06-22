package com.bosonit.formacion.events;

import com.bosonit.formacion.entity.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode (callSuper = true)
public class CustomerCreatedEvent extends Event <Customer>{
}
