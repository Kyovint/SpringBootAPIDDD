package com.kyovint.RetoUno.infraestructure.exceptions;

public class PersistenceException extends RuntimeException{

    public PersistenceException(Throwable message) {
        super(message);
    }
}
