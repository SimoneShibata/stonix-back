package com.escoladeti.oldowl.stonix.exception;

/**
 * Created by theonly on 2016-05-21.
 */
public class DontUseException extends RuntimeException {
    public DontUseException() {
        super("DO NOT USE THIS!");
    }
}
