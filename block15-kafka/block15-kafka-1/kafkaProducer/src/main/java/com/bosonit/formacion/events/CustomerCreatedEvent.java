package com.bosonit.formacion.events;

import com.bosonit.formacion.entity.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Clase que hereda de Event <T> en la que especificamos que 'T' va a ser un objeto 'Customer
 */
@Data
@EqualsAndHashCode (callSuper = true)
public class CustomerCreatedEvent extends Event <Customer>{
}
