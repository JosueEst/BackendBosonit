package com.bosonit.formacion.block6personcontrollers;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Data
@NoArgsConstructor
public class Person {
    String name, location,age;

}
