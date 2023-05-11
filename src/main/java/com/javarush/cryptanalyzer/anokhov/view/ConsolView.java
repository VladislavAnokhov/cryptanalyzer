package com.javarush.cryptanalyzer.anokhov.view;

import com.javarush.cryptanalyzer.anokhov.constants.AlfaBet;
import com.javarush.cryptanalyzer.anokhov.entity.Mode;
import com.javarush.cryptanalyzer.anokhov.entity.Result;

import java.io.InputStream;
import java.util.Scanner;

import static com.javarush.cryptanalyzer.anokhov.constants.ApplicationCommunication.*;
import static com.javarush.cryptanalyzer.anokhov.constants.FileWays.*;
import static com.javarush.cryptanalyzer.anokhov.constants.ResultConstants.*;
;

public class ConsolView implements View{
    private Mode mode;
    private String fileWay;
    private int key;


   /**    Метод создан для взаимодействия с пользователем,
     запрос от пользователя, какой режим он хочет выполнить
     и какой файл прочитать и записать, а также запросы на ключ . */
   @Override
    public Mode getParameter() {
        AlfaBet alfaBet = new AlfaBet();
       System.out.println(hi);
        Scanner scanner = new Scanner(System.in);
        boolean pravda = true;
        do {
            String string = scanner.nextLine();
            if (string.equalsIgnoreCase(encrypt)) {
                mode = new Mode(encrypt, getFileWay(encrypt, read), getFileWay(encrypt, write), getKey(encrypt));
                pravda = false;
            } else if (string.equalsIgnoreCase(decipher)) {
                mode = new Mode(decipher, getFileWay(decipher, read), getFileWay(decipher, write), getKey(decipher));
                pravda = false;
            } else if (string.equalsIgnoreCase(breaker)) {
                mode = new Mode(breaker, getFileWay(breaker, read), getFileWay(breaker, write));
                pravda = false;
            }
        }

        while (pravda);
        scanner.close();
        return mode;
    }

    /**                Этот метод возвращает путь к файлу.
       Метод смотрит какой режим хочет использовать пользователь и возвращает необходимый
       дефолтный файл(если такой необходим), либо возвращает указанный пользователем файл.
       Метод возвращает путь к файлу для чтения и для записи, смотря какой режим передать методу.*/
    private String getFileWay(String mode, String typeOfWay) {
        String defaultString = null;
        switch (typeOfWay){
            case read -> {
                System.out.println(localOrOwnFileForConsol);
                if (mode == encrypt)
                    defaultString = input;
                else defaultString = encoded;
            }
            case write -> {
                System.out.println(whereToSave);
                if (mode == encrypt)
                defaultString = encoded; //дефолтный файл при шифровки
            else defaultString = output; //дефолтный файл при расшифровки для разных способов
            }
        }
       fileWay = mineScanner(defaultString);
        System.out.println(wayOfFileIs+fileWay);
        return fileWay;
    }

    /**  Этот метод возвращает ключ, который указывает пользователь,
       Либо генирирует ключ согласно размеру алфавита. Метод
       принимает режим необходимый для пользователя.*/
    private int getKey(String mode) {
        String string = null;
        InputStream stream = System.in;
        Scanner console = new Scanner(stream);
        if (mode == encrypt ) {
            System.out.println(randomOrSetKeyForConsol);
            string = console.nextLine();
            if (string == "")
            key = 1 + (int) (Math.random() * (AlfaBet.alfaBet.size() - 1));
            else key = Integer.parseInt(string);
       }
        else {
            System.out.println(askKeyForDecipher);
            string = console.nextLine();
            key= Integer.parseInt(string);
        }
      System.out.println(secretKey+key);
        console.close();
        return key;
    }


    /** Сканер для ввода пути файла*/
    private String mineScanner (String defaultFile){
        InputStream stream = System.in;
        Scanner console = new Scanner(stream);
        fileWay = console.nextLine();
        if (fileWay =="" || fileWay.equals(""))
            fileWay =defaultFile;
        return fileWay;
    }

    @Override
    public void printResult(Result result) {
        if (key == 0 ){
            key = result.getKey();
        }
        switch (result.getResultOfCode()){
            case GOOD -> System.out.println(HAVEDONE+"\n"+secretKey+key);
            case ERROR-> System.out.println(EXCEPTION+result.getApplicationException().getMessage());
        }
    }
}