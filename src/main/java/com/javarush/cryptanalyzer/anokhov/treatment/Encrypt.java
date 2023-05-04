package com.javarush.cryptanalyzer.anokhov.treatment;
import com.javarush.cryptanalyzer.anokhov.alfabet.Alfa;
import com.javarush.cryptanalyzer.anokhov.screen.Screen;
import com.javarush.cryptanalyzer.anokhov.creatorNewFiles.NewFile;
import java.io.*;
import java.util.Scanner;

public class Encrypt {
    public static final Encrypt encrypt = new Encrypt();
    static  private int ci ;
    static private int pi;
    //key - секретный ключ для шифровки
    //pi - буква в тексте
    //ci - буква в шифрованном тексте
    public Encrypt(){
    }
    //метод для шифрования текста
    public static void encrypt(){

        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        String fileWay = scanner.nextLine();
        int key = scanner1.nextInt();
        scanner.close();
        scanner1.close();

        StringBuilder stringBuilder=new StringBuilder();
        try (FileReader in = new FileReader(fileWay); BufferedReader reader = new BufferedReader(in)) {
            while (reader.ready()) {
                //цикл для пропускания символов которых нету в нашем алфавите
                //если метод Alfa возратил -1, значит символа нету в алфавите, и метод перейдет
                //на другой символ в тексте без шифровки
                do {
                    pi = Alfa.numberOfAlfaBet((char) reader.read());
                }
                while (pi==-1);
                ci = (pi + key)% Alfa.alfaBet.size();
                stringBuilder.append(Alfa.alfaBet.get(ci));
            }

        }

        catch ( IOException e ){
            Screen.errorFile();
            System.err.println(e.getMessage());
        }
        NewFile newFile = new NewFile(fileWay,"encoded");

        try (FileWriter out = new FileWriter(newFile.file); BufferedWriter writer = new BufferedWriter(out)) {
            writer.write(String.valueOf(stringBuilder));
            System.out.println("сообщение зашифрованно");
        }

        catch ( IOException e ){
            Screen.errorFile();
            System.err.println(e.getMessage());
        }
    }

}
