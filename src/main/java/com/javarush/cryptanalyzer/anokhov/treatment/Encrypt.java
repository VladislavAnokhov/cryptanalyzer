package com.javarush.cryptanalyzer.anokhov.treatment;
import com.javarush.cryptanalyzer.anokhov.constants.AlfaBet;
import com.javarush.cryptanalyzer.anokhov.constants.ResultCode;
import com.javarush.cryptanalyzer.anokhov.entity.Mode;
import com.javarush.cryptanalyzer.anokhov.entity.Result;
import com.javarush.cryptanalyzer.anokhov.exceptions.ApplicationException;
import java.io.*;


public class Encrypt implements Function {
    static  private int ci ;
    static private int pi;
    //key - секретный ключ для шифровки
    //pi - буква в тексте
    //ci - буква в шифрованном тексте

    @Override
        /* метод, который зашифровывает символы файла в StringBuilder
        * Возвращает результат и зашифрованный текст ввиде StringBuilder*/
    public Result execute(Mode mode) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader in = new FileReader(mode.getFileWayForRead()); BufferedReader reader = new BufferedReader(in)) {
            while (reader.ready()) {

                /*цикл для пропускания символов которых нету в нашем алфавите :
                если метод Alfa возвратил -1, значит символа нету в алфавите, и метод перейдет
                на другой символ в тексте без шифровки*/
                do {
                    pi = AlfaBet.numberOfAlfaBet((char) reader.read());
                }
                while (pi == -1);
                ci = (pi + mode.getKey()) % AlfaBet.alfaBet.size();
                stringBuilder.append(AlfaBet.alfaBet.get(ci));
            }
        }
        catch (IOException e){
            return new Result(ResultCode.ERROR,new ApplicationException(ApplicationException.stringErrorOfFile,e));
    }
        return new Result(ResultCode.GOOD,stringBuilder);
    }
}
