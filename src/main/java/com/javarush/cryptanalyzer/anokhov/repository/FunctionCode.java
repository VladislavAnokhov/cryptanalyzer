package com.javarush.cryptanalyzer.anokhov.repository;

import com.javarush.cryptanalyzer.anokhov.treatment.*;


public enum FunctionCode {
    ENCRYPT(new Encrypt()),
    DECIPHER(new Decipher()),
    BREAKER(new Breaker()),
    UNSUPPORTED_FUNCTION(new UnsupportedFunction());

    private Function function;
    FunctionCode (Function function){
        this.function = function;
    }
    public Function getFunction (){
        return function;
    }
}
