package com.bosonit.formacion.block12mongodb.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CustomErrorOutputDto {
    Date timestamp;
    int httpCode;
    String message;
}
