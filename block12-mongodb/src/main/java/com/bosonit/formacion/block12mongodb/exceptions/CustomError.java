package com.bosonit.formacion.block12mongodb.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class CustomError extends Exception {

    public CustomError(String message){
        this.message = message;
    }
    Date timestamp;
    int httpCode;
    String message;

}
