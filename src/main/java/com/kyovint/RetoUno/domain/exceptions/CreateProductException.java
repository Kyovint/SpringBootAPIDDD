package com.kyovint.RetoUno.domain.exceptions;

public class CreateProductException extends RuntimeException {

    //This method waits for a message if a exception is executed
    public CreateProductException(String message) {
        super(message);
    }
}
