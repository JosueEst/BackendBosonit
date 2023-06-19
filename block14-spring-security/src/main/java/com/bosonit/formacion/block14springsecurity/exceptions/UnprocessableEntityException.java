package com.bosonit.formacion.block14springsecurity.exceptions;

public class UnprocessableEntityException extends Exception{

    public UnprocessableEntityException (){
        super ("422 (UNPROCESSABLE ENTITY): A field or some of them are not valid");
    }
    public UnprocessableEntityException (String message){
        super(message);
    }
}
