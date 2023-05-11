package com.javarush.cryptanalyzer.anokhov.view;
import com.javarush.cryptanalyzer.anokhov.constants.AlfaBet;
import com.javarush.cryptanalyzer.anokhov.jframeWindows.JFrameResultWindow;
import com.javarush.cryptanalyzer.anokhov.repository.WindowsCode;
import com.javarush.cryptanalyzer.anokhov.controller.JFrameWindowModeController;
import com.javarush.cryptanalyzer.anokhov.entity.Mode;
import com.javarush.cryptanalyzer.anokhov.entity.Result;
import com.javarush.cryptanalyzer.anokhov.jframeWindows.Window;
import javax.swing.*;
import static com.javarush.cryptanalyzer.anokhov.constants.ApplicationCommunication.*;
import static com.javarush.cryptanalyzer.anokhov.constants.WindowsConstants.*;

public class SwingView extends JFrame implements View {
  private JFrameWindowModeController modeController;

private Mode mode;
    @Override
    public Mode getParameter() {
        AlfaBet alfaBet = new AlfaBet();
        modeController = new JFrameWindowModeController();
         Window window = getWindow(modeController.getModeStatus());
            window.run(modeController.getModeStatus());
            mode = window.getInformation();

        return mode ;
    }


    @Override
    public void printResult(Result result) {
        JFrameResultWindow resultWindow = new JFrameResultWindow();
        resultWindow.run(result);
    }



    public JFrameWindowModeController getModeController (){
        return modeController;
    }
    private  Window getWindow (String state){
        return switch (state){
            case encrypt, decipher,breaker -> WindowsCode.valueOf(JFRAME_WINDOW).getWindow();
            default -> WindowsCode.valueOf(UNSUPPORTED_WINDOW).getWindow();
        };
    }
}