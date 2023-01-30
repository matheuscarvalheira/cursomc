package com.matheus.cursomc.services.exceptions;

public class ObjectNotFoundtException extends RuntimeException {

    public ObjectNotFoundtException(String msg){
        super(msg);
    }

    public ObjectNotFoundtException(String msg, Throwable cause){
        super(msg,cause);
    }

}
