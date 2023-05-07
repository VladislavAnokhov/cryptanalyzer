package com.javarush.cryptanalyzer.anokhov.exceptions;

public class ApplicationException extends Exception {

    public static final String stringErrorOfFile ="ошибка работы с файлом";
public  ApplicationException(){

}
public ApplicationException(String massage){
    super(massage);
}
public ApplicationException(String massage, Throwable cause ){
    super(massage,cause);
}
public ApplicationException(Throwable cause){
    super(cause);
}

}

