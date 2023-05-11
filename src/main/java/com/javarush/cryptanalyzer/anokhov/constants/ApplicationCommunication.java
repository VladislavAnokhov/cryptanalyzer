package com.javarush.cryptanalyzer.anokhov.constants;


public class ApplicationCommunication {
    static public final String encrypt = "Зашифровать";
    static public final String decipher ="Расшифровать";
    static public final String breaker ="Взломать";
    static public final String mainWelcome ="  Добро пожаловать, дорогой пользователь! Данный проект создан для зашифровки  \n"+
           "и расшифровки текста методом шифра Цезаря. Расшифровать текст можно двумя способами:\n указать ключ или " +
           "грубым подбором ключей (Brute Force). Прежде чем начать, пожалуйста, укажите в каком ввиде Вы хотите использовать приложение." +
            " Приятного использования !";
    static public final String welcome = "Добро пожаловать! Какое действие вы хотите совершить c файлом?";


    static public final String hi ="Вы хотите зашифровать или расшифровать файл? Или взломать шифр? (Напишите словами. Пример:\"взломать\")";
    static public final String errorOfKey = "ВНИМАНИЕ! Значение ключа превысило размер Алфавита. " +
            "Неудалось расшифровать файл по указанным паттарнам.";

    static public final String localOrOwnFileForConsol = "Если хотите использовать файл по умолчанию, нажмите \"ENTER\"."+
            " Если хотите использовать другой файл, укажите путь к нему: ";
    static public final String randomOrSetKeyForConsol = "Если хотите сгенирировать ключ для шифровки, нажмите \"ENTER\"." +
            " Если хотите использовать свой ключ, укажите его : ";
    static public final String setWayForFiles = "Укажите какие файлы обработать";
    static public final String genericKey = "Сгенерировать ключ";
    static public final String encryptedFile = "Зашифрованный файл: ";
    static public final String encryptFile = "Зашифровать файл: ";
    static public final String decryptFile = "Расшифровать файл: " ;
    static public final String decryptedFile = "Расшифрованным файлом будет: ";
    static public final String useDefaultFile = "файл по умолчанию";
    static public final String askKeyForDecipher = "Введите ключ для расшифровки файла";
    static public final String whereToSave ="Если вы хотите сохранить зишифрованный текст в файл по умолчанию, нажмите \"ENTER\"." +
            " Либо укажите путь самостоятельно: ";
    static public final String secretKey= "Секретный ключ: ";
    static public final String wayOfFileIs = "Путь к файлу: ";
    static public final String errorOfWindow = "Все поля должны быть заполнены!";

}
