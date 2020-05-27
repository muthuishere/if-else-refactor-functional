package com.deemwar.products.api;

import lombok.Data;

import java.util.function.Supplier;

@Data
public class Rule<T> {
    Supplier<Boolean> rule=null;
    Supplier<T> process=null;

    public Rule() {

    }



    public Rule(Supplier<Boolean> rule, Supplier<T> process) {
        this.rule = rule;
        this.process = process;
    }

    public Boolean isApplicable(){
        return rule.get();
    }
    public T applyProcess(){
        return process.get();
    }
}
