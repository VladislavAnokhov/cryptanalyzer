package com.javarush.cryptanalyzer.anokhov.treatment;
import com.javarush.cryptanalyzer.anokhov.alfabet.Alfa;
import com.javarush.cryptanalyzer.anokhov.creatorNewFiles.NewFile;
import com.javarush.cryptanalyzer.anokhov.screen.Screen;
import java.io.*;
import java.util.Scanner;

public class Decipher {
    public static final Decipher decipher = new Decipher();
    static private int ci;
    static private char pi;

    private Decipher() {
    }

    public static void decoding() {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        String fileWay = scanner.nextLine();
        int  key= scanner1.nextInt();
        scanner.close();
        scanner1.close();

        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader in = new FileReader(fileWay); BufferedReader reader = new BufferedReader(in)) { // нужно сделать чтоб этот же файл изменялся
            while (reader.ready()) {
                pi =(char) reader.read();
                ci = Alfa.numberOfAlfaBet(pi) - key;
                if (ci <0){
                    ci = Alfa.alfaBet.size()+ci;}
                stringBuilder.append(Alfa.alfaBet.get(ci));
            }

        } catch (IOException e) {
            Screen.errorFile();
            System.out.println(e.getMessage());
        }

        NewFile newFile = new NewFile(fileWay,"output");
        try (FileWriter out = new FileWriter(newFile.file); BufferedWriter writer = new BufferedWriter(out)){
            writer.write(String.valueOf(stringBuilder));
            System.out.println("сообщение расшифрованно");
        } catch (IOException e) {
            Screen.errorFile();
            System.out.println(e.getMessage());
        }
    }
}
