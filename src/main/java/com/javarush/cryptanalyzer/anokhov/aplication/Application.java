package com.javarush.cryptanalyzer.anokhov.aplication;

import com.javarush.cryptanalyzer.anokhov.repository.FunctionCode;
import com.javarush.cryptanalyzer.anokhov.repository.ResultCode;
import com.javarush.cryptanalyzer.anokhov.controller.MainController;
import com.javarush.cryptanalyzer.anokhov.creatorNewFiles.NewFile;
import com.javarush.cryptanalyzer.anokhov.entity.Mode;
import com.javarush.cryptanalyzer.anokhov.entity.Result;
import com.javarush.cryptanalyzer.anokhov.treatment.Function;


import static com.javarush.cryptanalyzer.anokhov.constants.ApplicationCommunication.*;
import static com.javarush.cryptanalyzer.anokhov.constants.FunctionConstants.*;


public class Application  {
    private final MainController mainController;
    private Result finishResult;

    public Application(MainController mainController){
        this.mainController=mainController;
    }

    public Result run(){
        Mode parameters = mainController.getView().getParameter();
        String mode = parameters.getMode();
        Function function = getFunction(mode);
       Result result =  function.execute(parameters);
        if (result.getResultOfCode()== ResultCode.GOOD) {
          finishResult =NewFile.newFile(parameters.getFileWayForWrite(),result.getStringBuilder(),result.getKey());

        }

        return finishResult;
    }


   /** Вызов необходимой функции программы*/
private Function getFunction (String mode){

    return switch (mode){
            case encrypt -> FunctionCode.valueOf(ENCRYPT).getFunction();
            case decipher -> FunctionCode.valueOf(DECIPHER).getFunction();
            case breaker -> FunctionCode.valueOf(BREAKER).getFunction();
        default -> FunctionCode.valueOf(UNSUPPORTED_FUNCTION).getFunction();
        };


}
public void printResult(Result result ){
        mainController.getView().printResult(result);
}
}
