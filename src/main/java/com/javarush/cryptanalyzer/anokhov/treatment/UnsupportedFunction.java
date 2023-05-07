package com.javarush.cryptanalyzer.anokhov.treatment;

import com.javarush.cryptanalyzer.anokhov.entity.Mode;
import com.javarush.cryptanalyzer.anokhov.entity.Result;

public class UnsupportedFunction implements Function {


    @Override
    public Result execute(Mode mode) {
        return null;
    }
}
