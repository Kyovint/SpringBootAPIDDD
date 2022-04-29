package com.kyovint.RetoUno.infraestructure.exceptions;

public class AlreadyExistsException extends RuntimeException{

    public AlreadyExistsException(String message) {
        super(message);
    }

}
