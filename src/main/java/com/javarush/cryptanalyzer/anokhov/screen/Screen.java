package com.javarush.cryptanalyzer.anokhov.screen;
import com.javarush.cryptanalyzer.anokhov.treatment.Encrypt;
import com.javarush.cryptanalyzer.anokhov.treatment.Decipher;
import com.javarush.cryptanalyzer.anokhov.treatment.Breaker;

import java.util.Scanner;

public class Screen {
    public static final Screen console = new Screen();
    static private final String encrypt = "Зашифровать";
    static private final String decipher ="Расшифровать";
    static private final String brute ="Взломать";

    static private final String hi = "Аве Повелитель, идущие на смерть приветствуют тебя!" +
            " Вы хотите зашифровать или расшифровать файл или взломать шифр?";
    static private final String errorOfFile = "Ошибка ратобы с файлом: ";
    static private final String fileForEncrypt = "Введите файл, который хотите зашифровать и ключ для шифровки";
    static private final String fileForDecipher = "Введите файл, который хотите расшифровать и ключ";
    static private final String fileForBruteForce = "Введите файл,в котором хотите взломать шифр";

    private Screen(){
    }
    static public void saysAndAsk (){
        System.out.println(hi);
        Scanner scanner = new Scanner(System.in);
        boolean pravda = true;
        do {
            String string = scanner.nextLine();

            if (string.equalsIgnoreCase(encrypt)) {
                System.out.println(fileForEncrypt);
                Encrypt.encrypt();
                scanner.close();
                pravda=false;
                continue;
            }
            if (string.equalsIgnoreCase(decipher)) {
                System.out.println(fileForDecipher);
                Decipher.decoding();
                scanner.close();
                pravda=false;
                continue;
            }
            if (string.equalsIgnoreCase(brute)){
                System.out.println(fileForBruteForce);
                Breaker.bruteForce();
                scanner.close();
                pravda=false;

            }

        }
        while (pravda);
    }
    static public void errorFile (){
        System.err.print(errorOfFile);
    }
}
