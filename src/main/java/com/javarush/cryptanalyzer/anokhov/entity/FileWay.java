package com.javarush.cryptanalyzer.anokhov.entity;

import java.io.InputStream;
import java.util.Scanner;

import static com.javarush.cryptanalyzer.anokhov.constants.ApplicationCommunication.*;
import static com.javarush.cryptanalyzer.anokhov.constants.FileWays.*;
import static com.javarush.cryptanalyzer.anokhov.constants.FileWays.output;

public class FileWay {
    private String fileWay;

    /**                Этот метод возвращает дефолтный путь к файлу.
     Метод смотрит какой режим хочет использовать пользователь и возвращает необходимый
     дефолтный путь к файлу*/
    public String getFileWay(String mode, String typeOfWay) {
        String defaultString = null;
        switch (typeOfWay){
            case read -> {

                if (mode == encrypt)
                    defaultString = input;
                else defaultString = encoded;
            }
            case write -> {

                if (mode == encrypt)
                    defaultString = encoded; //дефолтный файл при шифровки
                else defaultString = output; //дефолтный файл при расшифровки для разных способов
            }
        }
       return defaultString;
    }

}
