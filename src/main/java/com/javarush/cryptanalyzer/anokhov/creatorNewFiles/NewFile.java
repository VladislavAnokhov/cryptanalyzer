package com.javarush.cryptanalyzer.anokhov.creatorNewFiles;
import com.javarush.cryptanalyzer.anokhov.repository.ResultCode;
import com.javarush.cryptanalyzer.anokhov.entity.Result;
import com.javarush.cryptanalyzer.anokhov.exceptions.ApplicationException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class NewFile {

    /**Метод для создания файлов. Получает путь, в котором указано куда
    сохранять данные. Также получает сами данные для записи.
    Возвращает результат записи*/
    public static Result newFile( String fileWayForWrite, StringBuilder stringBuilder,int key)  {

        try (FileWriter out = new FileWriter(fileWayForWrite); BufferedWriter writer = new BufferedWriter(out)){
            writer.write(String.valueOf(stringBuilder));
        }
        catch (IOException e){
            return new Result(ResultCode.ERROR,new ApplicationException(ApplicationException.stringErrorOfFile,e));
        }
        return new Result(ResultCode.GOOD,stringBuilder,key);

    }
}
