package com.javarush.cryptanalyzer.anokhov;

import com.javarush.cryptanalyzer.anokhov.aplication.Application;
import com.javarush.cryptanalyzer.anokhov.controller.MainController;
import com.javarush.cryptanalyzer.anokhov.entity.Mode;
import com.javarush.cryptanalyzer.anokhov.entity.Result;
import com.javarush.cryptanalyzer.anokhov.exceptions.ApplicationException;

import com.javarush.cryptanalyzer.anokhov.view.ConsolView;
import com.javarush.cryptanalyzer.anokhov.view.View;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;

public class StartPoint {
    public static void main(String[] args) {

        View view = new ConsolView();
        MainController mainController = new MainController(view);
        Application application = new Application(mainController);
        Result  result= application.run();
        application.printResult(result);

    }
}


