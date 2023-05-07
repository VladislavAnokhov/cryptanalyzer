package com.javarush.cryptanalyzer.anokhov.entity;

import com.javarush.cryptanalyzer.anokhov.exceptions.ApplicationException;
import com.javarush.cryptanalyzer.anokhov.constants.ResultCode;

public class Result {
    private ResultCode resultOfCode;
    private ApplicationException applicationException;
    private StringBuilder stringBuilder;


    public Result(ResultCode resultOfCode, StringBuilder stringBuilder){
        this.resultOfCode=resultOfCode;
        this.stringBuilder = stringBuilder;
    }
    public Result(ResultCode resultCode,ApplicationException applicationException){
        this.resultOfCode = resultCode;
        this.applicationException=applicationException;
    }
    public ResultCode getResultOfCode() {
        return resultOfCode;
    }
    public ApplicationException getApplicationException(){
        return applicationException;
    }

    public StringBuilder getStringBuilder(){
        return stringBuilder;
    }
}
