package com.javarush.cryptanalyzer.anokhov.constants;

import java.util.ArrayList;
import java.util.List;

public class AlfaBet {

    public static final List<Character> alfaBet =new ArrayList<>();
    private static  final String lettersUpperCase = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String getLettersLowerCase = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String numbers = "0123456789";
    private static final  String symbols  = ".,\":-!? \n\t";
    private static final String ALPHABET = lettersUpperCase+getLettersLowerCase+numbers+symbols;
    public AlfaBet() {
        for (int i = 0; i < ALPHABET.length(); i++) {
            alfaBet.add(ALPHABET.charAt(i));
        }
    }

    /**Метод, с помощью которого получаем номер символа в списке.
    * Метод может вернуть значение -1, если символ не был найден в списке.*/
    static public int numberOfAlfaBet(char character) {
        int charNumber=0;
        for (char bukva : AlfaBet.alfaBet) {
            if (character == bukva) {
                charNumber = AlfaBet.alfaBet.indexOf(bukva);
                break;
            }
            else charNumber=-1;
        }
        return charNumber;
    }
}

