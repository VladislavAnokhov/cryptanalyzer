package com.javarush.cryptanalyzer.anokhov.repository;

import com.javarush.cryptanalyzer.anokhov.jframeWindows.JFrameApplicationWindow;
import com.javarush.cryptanalyzer.anokhov.jframeWindows.UnsupportedWindow;
import com.javarush.cryptanalyzer.anokhov.jframeWindows.Window;

public enum WindowsCode {
    JFRAME_WINDOW(new JFrameApplicationWindow()),
    UNSUPPORTED_WINDOW(new UnsupportedWindow());

    private Window window;
     WindowsCode (Window window){
         this.window=window;
    }
    public Window getWindow(){
         return window;
    }
}
