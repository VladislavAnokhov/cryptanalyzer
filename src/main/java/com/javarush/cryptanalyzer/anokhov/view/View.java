package com.javarush.cryptanalyzer.anokhov.view;

import com.javarush.cryptanalyzer.anokhov.entity.Mode;
import com.javarush.cryptanalyzer.anokhov.entity.Result;

public interface View {
    Mode getParameters();

    void printResult (Result result);
}
