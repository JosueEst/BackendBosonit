package com.bosonit.formacion.block14springsecurity.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class CustomError extends Exception {
    Date timestamp;
    int httpCode;
    String message;

}
