package com.bosonit.formacion.block7crudvalidation.Exceptions;

import org.springframework.web.client.HttpClientErrorException;

public class UnprocessableEntityException extends Exception{

    public UnprocessableEntityException (){
        super ("422 (UNPROCESSABLE ENTITY): A field or some of them are not valid");
    }
    public UnprocessableEntityException (String message){
        super(message);
    }
}
