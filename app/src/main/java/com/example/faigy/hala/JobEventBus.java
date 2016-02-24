package com.example.faigy.hala;

/**
 * Created by Home on 2/18/2016.
 */
public class JobEventBus {

    private String message;

    public JobEventBus (String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
