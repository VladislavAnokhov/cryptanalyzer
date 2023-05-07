package com.javarush.cryptanalyzer.anokhov.constants;

public class ApplicationCommunication {
    static public final String encrypt = "зашифровать";
    static public final String decipher ="расшифровать";
    static public final String breaker ="взломать";

    static public final String hi = "Аве Повелитель, идущие на смерть приветствуют тебя!" +
            " Вы хотите зашифровать или расшифровать файл или взломать шифр?";

    static public final String localOrOwnFile = "Если хотите использовать файл по умолчанию, нажмите \"ENTER\"."+
            " Если хотите использовать другой файл, укажите путь к нему: ";
    static public final String randomOrSetKey = "Если хотите сгенирировать ключ для шифровки, нажмите \"ENTER\". +" +
            " Если хотите использовать свой ключ, укажите его : ";
    static public final String askKeyForDecipher = "Введите ключ для расшифровки файла";
    static public final String whereToSave ="Если вы хотите сохранить зишифрованный текст в файл по умолчанию, нажмите \"ENTER\". +" +
            "Либо укажите путь самостоятельно: ";
    static public final String secretKey= "Секретный ключ: ";
    static public final String wayOfFileIs = "Путь к файлу: ";
}
