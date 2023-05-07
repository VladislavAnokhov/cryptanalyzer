package com.javarush.cryptanalyzer.anokhov.treatment;
import com.javarush.cryptanalyzer.anokhov.constants.AlfaBet;
import com.javarush.cryptanalyzer.anokhov.constants.ResultCode;
import com.javarush.cryptanalyzer.anokhov.entity.Mode;
import com.javarush.cryptanalyzer.anokhov.entity.Result;
import com.javarush.cryptanalyzer.anokhov.exceptions.ApplicationException;
import java.io.*;

public class Decipher implements Function {
    static private int ci;
    static private char pi;
    //key - секретный ключ для шифровки
    //pi - буква в тексте
    //ci - буква в шифрованном тексте
@Override
     /* Метод для расшифровки текста. Возвращает результат процесса,
     * а также расшифрованный текс ввиде StringBuilder */
    public Result execute(Mode mode) {
        StringBuilder stringBuilder = new StringBuilder();

        try (FileReader in = new FileReader(mode.getFileWayForRead()); BufferedReader reader = new BufferedReader(in)) { // нужно сделать чтоб этот же файл изменялся
            while (reader.ready()) {
                pi = (char) reader.read();
                ci = AlfaBet.numberOfAlfaBet(pi) - mode.getKey();
                if (ci < 0) {
                    ci = AlfaBet.alfaBet.size() + ci;
                }
                stringBuilder.append(AlfaBet.alfaBet.get(ci));
            }
        }
         catch (IOException e){
                return new Result(ResultCode.ERROR,new ApplicationException(ApplicationException.stringErrorOfFile,e));
            }
            return new Result(ResultCode.GOOD,stringBuilder);

    }
}
