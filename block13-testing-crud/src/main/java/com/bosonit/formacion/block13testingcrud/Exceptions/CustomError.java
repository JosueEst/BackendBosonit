package com.bosonit.formacion.block13testingcrud.Exceptions;

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
