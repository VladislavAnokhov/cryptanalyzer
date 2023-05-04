package com.javarush.cryptanalyzer.anokhov.treatment;
import com.javarush.cryptanalyzer.anokhov.alfabet.Alfa;
import com.javarush.cryptanalyzer.anokhov.screen.Screen;
import com.javarush.cryptanalyzer.anokhov.creatorNewFiles.NewFile;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Breaker {
    private static final String keyString = "ключом шифра был: ";
    public static final Breaker breaker = new Breaker();
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

    private static Pattern patternSpaces = Pattern.compile(".\s.");
    //паттерн для поиска пробелов в тексте между слов
    private static Pattern patternCommas = Pattern.compile(".\\,\s.");
    //паттерн для поиска запятых, после которых стоит пробел
    private static Pattern patternOfFalseCommas = Pattern.compile(".\\,\\S");
    //паттерн для поиска запятых, после который стоит что-либо кроме пробела
    private static Pattern patternPointInTheEnd = Pattern.compile("[$А-ЯЁ].+[\\.!?]");
    //паттерн для поиска предложений, которые начинаются с большой буквы и заканчиваются точкой
    private Breaker (){
    }
    public static void bruteForce () {
        Scanner scanner = new Scanner(System.in);
        String fileWay = scanner.nextLine();
        StringBuilder builder = new StringBuilder();
        StringBuilder bufferForBreaker = new StringBuilder();
        scanner.close();
        try (FileReader in = new FileReader(fileWay); BufferedReader reader = new BufferedReader(in)) {
            boolean pravda = false;
            while (reader.ready()) {
                builder.append((char) reader.read());
            }
            //цикл перебора ключей
            do {
                for (int i = 0; i < builder.length(); i++) {
                    pi = builder.charAt(i);
                    ci = Alfa.numberOfAlfaBet(pi) - key;
                    if (ci <0){
                        ci = Alfa.alfaBet.size()+ci;}
                    bufferForBreaker.append(Alfa.alfaBet.get(ci));}
                pravda = checking(bufferForBreaker);

                //если поиск по паттернам вернулся false ключ становится на 1 больше и обнуляется StringBuilder
                if (!pravda){
                    key++;
                    bufferForBreaker.setLength(0);}
               /* if (key>Alfa.alfaBet.size()){
                    System.err.println("неверный код");
                    break;
                }*/
            }
            while (!pravda);
            System.out.println(keyString + key);

        } catch (IOException e) {
            Screen.errorFile();
            System.out.println(e.getMessage());
        }

        NewFile newFile = new NewFile(fileWay, "output");
        try (FileWriter out = new FileWriter(newFile.file); BufferedWriter writer = new BufferedWriter(out)) {
            writer.write(String.valueOf(bufferForBreaker));
        }
        catch (IOException e) {
            Screen.errorFile();
            System.out.println(e.getMessage());
        }
    }
   // проверка совпадений в тексте.
    private static Boolean checking(StringBuilder builder){
        Boolean result = false;
        spaces =  Breaker.count(String.valueOf(builder), patternSpaces);
        points = Breaker.count(String.valueOf(builder), patternPointInTheEnd);
        commas = Breaker.count(String.valueOf(builder), patternCommas);
        falseCommas = Breaker.count(String.valueOf(builder),patternOfFalseCommas);

        //условия верности расшифровки файла
        //минимум три пробела, одна точка и одна запятая (например, если есть только одно предложение)
        //должно быть 0 совпадений с неправильной выставленной запятой
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
