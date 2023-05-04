package com.javarush.cryptanalyzer.anokhov.alfabet;

import java.util.ArrayList;
import java.util.List;

public class Alfa {
    public static final List<Character> alfaBet =new ArrayList<>();

    private static  final String lettersUpperCase = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String getLettersLowerCase = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String numbers = "0123456789";
    private static final  String symbols  = ".,\":-!? ";

    private static final String ALPHABET = lettersUpperCase+getLettersLowerCase+numbers+symbols;

    public static final Alfa alfa = new Alfa();
    private Alfa() {
        for (int i = 0; i < ALPHABET.length(); i++) {
            alfaBet.add(ALPHABET.charAt(i));
        }
    }
    static public int numberOfAlfaBet(char character) { //возврщает индекса буквы/символа из массива
        int charNumber=0;
        for (char bukva : Alfa.alfaBet) {
            if (character == bukva) {
                charNumber = Alfa.alfaBet.indexOf(bukva);
                break;
            }
            else charNumber=-1;
        }

        return charNumber;
    }
}

