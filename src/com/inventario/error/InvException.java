package com.inventario.error;

/**
 *
 * @author José Bernardo Goméz-Andrade
 */
public class InvException extends Exception {

    public InvException(String message) {
        super(message);
    }

    public InvException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvException(Throwable cause) {
        super(cause);
    }

}
