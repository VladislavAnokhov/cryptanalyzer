package com.javarush.cryptanalyzer.anokhov.controller;

import com.javarush.cryptanalyzer.anokhov.view.View;

public class MainController {

    private View view ;
    public  MainController(View view){
        this.view=view;
    }

    public View getView() {
        return view;
    }
}
