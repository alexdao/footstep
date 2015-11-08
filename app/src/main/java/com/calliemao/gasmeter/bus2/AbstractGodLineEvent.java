package com.calliemao.gasmeter.bus2;

/**
 * Created by alex on 8/11/15.
 */
public abstract class AbstractGodLineEvent {

    private Enum type;

    protected AbstractGodLineEvent(Enum type) {
        this.type = type;
    }

    public Enum getType() {
        return this.type;
    }
}