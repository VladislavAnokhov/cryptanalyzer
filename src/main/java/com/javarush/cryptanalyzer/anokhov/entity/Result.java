package com.javarush.cryptanalyzer.anokhov.entity;

import com.javarush.cryptanalyzer.anokhov.exceptions.ApplicationException;
import com.javarush.cryptanalyzer.anokhov.repository.ResultCode;

public class Result {
    private ResultCode resultOfCode;
    private ApplicationException applicationException;
    private StringBuilder stringBuilder;

    private int key;

    /**
     * класс создан для передачи итоговой информации
     * между различными блоками кода
     * @param resultOfCode
     * @param stringBuilder
     * @param key
     */

    public Result(ResultCode resultOfCode, StringBuilder stringBuilder, int key) {
        this.resultOfCode = resultOfCode;
        this.stringBuilder = stringBuilder;
        this.key = key;
    }
    public Result(ResultCode resultOfCode, StringBuilder stringBuilder) {
        this.resultOfCode = resultOfCode;
        this.stringBuilder = stringBuilder;

    }

    public Result(ResultCode resultCode, ApplicationException applicationException) {
        this.resultOfCode = resultCode;
        this.applicationException = applicationException;
    }

    public ResultCode getResultOfCode() {
        return resultOfCode;
    }

    public ApplicationException getApplicationException() {
        return applicationException;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public int getKey() {
    return key;
    }
}