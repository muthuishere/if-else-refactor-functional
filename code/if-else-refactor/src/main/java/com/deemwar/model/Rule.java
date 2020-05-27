package com.deemwar.model;

import lombok.Data;

import java.util.function.Supplier;

@Data
public class Rule<T> {
    public Supplier<Boolean> condition = null;
    public  Supplier<T> process = null;

  public Rule() {

    }



    public Rule(Supplier<Boolean> condition, Supplier<T> process) {
        this.condition = condition;
        this.process = process;
    }

    public Boolean isApplicable(){
        return condition.get();
    }
    public T applyProcess(){
        return process.get();
    }
}
