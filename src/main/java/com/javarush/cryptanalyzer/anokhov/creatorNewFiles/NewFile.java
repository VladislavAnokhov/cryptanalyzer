package com.javarush.cryptanalyzer.anokhov.creatorNewFiles;
import com.javarush.cryptanalyzer.anokhov.screen.Screen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class NewFile {

    public static File file;

    public NewFile(String fileWay, String nameFile )  {

        Path path = Path.of(fileWay);
        file = new File( path.getParent().toString()+ System.getProperty("file.separator") + nameFile+".txt");
        if (file.exists()) {
            file.delete();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Screen.errorFile();
                System.err.println(e.getMessage());
            }
        }
    }
}
