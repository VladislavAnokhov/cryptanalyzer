package com.javarush.cryptanalyzer.anokhov.jframeWindows;

import com.javarush.cryptanalyzer.anokhov.entity.Mode;

import javax.swing.*;

public class UnsupportedWindow extends JFrame implements Window {

   private Mode modeStatus;
    @Override
    public synchronized Mode getInformation() {

        return null;
    }

    @Override
    public void run(String mode) {

    }
}
