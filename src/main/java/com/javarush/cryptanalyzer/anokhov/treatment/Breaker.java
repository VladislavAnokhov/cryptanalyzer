package com.javarush.cryptanalyzer.anokhov.treatment;
import com.javarush.cryptanalyzer.anokhov.constants.AlfaBet;
import com.javarush.cryptanalyzer.anokhov.constants.ResultCode;
import com.javarush.cryptanalyzer.anokhov.entity.Mode;
import com.javarush.cryptanalyzer.anokhov.entity.Result;
import com.javarush.cryptanalyzer.anokhov.exceptions.ApplicationException;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.javarush.cryptanalyzer.anokhov.constants.ApplicationCommunication.secretKey;

public class Breaker implements Function {
    private static int key=1;
    private static int spaces;
    private static int commas;
    private static int points;
    private static int falseCommas;
    private static char pi;
    private static int ci;
    //key - секретный ключ для шифровки
    //pi - буква в тексте
    //ci - буква в шифрованном тексте


       /*паттерн для поиска пробелов в тексте между слов*/
    private static Pattern patternSpaces = Pattern.compile(".\s.");
      /*паттерн для поиска запятых, после которых стоит пробел*/
    private static Pattern patternCommas = Pattern.compile(".\\,\s.");
    /*паттерн для поиска запятых, после который стоит что-либо кроме пробела
    Пример: слово ,слово - неправильная выставленная запятая*/
    private static Pattern patternOfFalseCommas = Pattern.compile(".\\,\\S");

    /*паттерн для поиска предложений, которые начинаются с большой буквы и заканчиваются точкой*/
    private static Pattern patternPointInTheEnd = Pattern.compile("[$А-ЯЁ].+[\\.!?]");


    /* Метод для грубой переборки ключей. Тут создано два StringBuilder. Один получает данные из файла,
    второй создан для проверки паттернов. Если проверку не проходит, он обнуляется и заново получает
    данные из первого StringBuilder. Возвращает результат, а также расшифрованный текст ввиде StringBuilder.
    В конце метода выводит на экран ключ,который подобрал метод*/
    @Override
    public Result  execute(Mode mode) {
        StringBuilder builder = new StringBuilder();
        StringBuilder bufferForBreaker = new StringBuilder();
        try (FileReader in = new FileReader(mode.getFileWayForRead()); BufferedReader reader = new BufferedReader(in)) {
            boolean pravda = false;
            while (reader.ready()) {
                builder.append((char) reader.read());
            }
            //цикл перебора ключей
            do {
                for (int i = 0; i < builder.length(); i++) {
                    pi = builder.charAt(i);
                    ci = AlfaBet.numberOfAlfaBet(pi) - key;
                    if (ci < 0) {
                        ci = AlfaBet.alfaBet.size() + ci;
                    }
                    bufferForBreaker.append(AlfaBet.alfaBet.get(ci));
                }
                pravda = checking(bufferForBreaker);

                if (!pravda) {                //если поиск по паттернам вернулся false, ключ становится
                    key++;                    // на 1 больше и обнуляется StringBuilder
                    bufferForBreaker.setLength(0);
                }
               else if (key > AlfaBet.alfaBet.size()) {
                    System.err.println("неверный код");
                    break;
                }
            }
            while (!pravda);
            System.out.println(secretKey+key);
        }
        catch (IOException e){
                return new Result(ResultCode.ERROR,new ApplicationException(ApplicationException.stringErrorOfFile,e));
            }
            return new Result(ResultCode.GOOD,bufferForBreaker);
    }


     /*  Метод,который проверяет совпадения по паттернам в тексте.
       Условия верности расшифровки файла:
       -минимум три пробела, одна точка и одна запятая (например, если есть только одно предложение)
       -должно быть 0 совпадений с неправильной выставленной запятой*/
    private static Boolean checking(StringBuilder builder){
        Boolean result = false;
        spaces =  Breaker.count(String.valueOf(builder), patternSpaces);
        points = Breaker.count(String.valueOf(builder), patternPointInTheEnd);
        commas = Breaker.count(String.valueOf(builder), patternCommas);
        falseCommas = Breaker.count(String.valueOf(builder),patternOfFalseCommas);

        if (spaces>=3 && points>=1 && commas>= 1 && falseCommas==0) {
            result = true;
        }
        return result;
    }

    // метод для поиска паттернов
    private static int count(String string , Pattern pattern) {
        Matcher matcher = pattern.matcher(string);
        int result = 0;
        while (matcher.find()) {
            result++;
        }
        return result;
    }


}
