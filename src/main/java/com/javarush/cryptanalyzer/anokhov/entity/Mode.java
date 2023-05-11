package com.javarush.cryptanalyzer.anokhov.entity;

public class Mode {
    private static int key ;
    private static String mode;
    private static String fileWayForRead;
    private static String fileWayForWrite;

    /**Класс создан для передачи необходимых параметров
     * под определенный режим, который захочет пользователь
     */

    public Mode (String mode,String fileWayForRead,String fileWayForWrite ,int key){
        this.mode=mode;
        this.fileWayForRead=fileWayForRead;
        this.fileWayForWrite=fileWayForWrite;
        this.key=key;

    }
    public Mode (String mode,String fileWayForRead,String fileWayForWrite){
        this.mode=mode;
        this.fileWayForRead=fileWayForRead;
        this.fileWayForWrite=fileWayForWrite;
    }

    public  int getKey() {
        return key;
    }

    public  String getMode() {
        return mode;
    }

    public  String getFileWayForRead() {
        return fileWayForRead;
    }
    public  String getFileWayForWrite() {
        return fileWayForWrite;
    }
}
