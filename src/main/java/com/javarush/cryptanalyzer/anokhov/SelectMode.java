package com.javarush.cryptanalyzer.anokhov;

import com.javarush.cryptanalyzer.anokhov.aplication.Application;
import com.javarush.cryptanalyzer.anokhov.controller.MainController;
import com.javarush.cryptanalyzer.anokhov.entity.Result;
import com.javarush.cryptanalyzer.anokhov.jframeWindows.JFrameWelcomeWindow;
import com.javarush.cryptanalyzer.anokhov.view.ConsolView;
import com.javarush.cryptanalyzer.anokhov.view.SwingView;
import com.javarush.cryptanalyzer.anokhov.view.View;

import static com.javarush.cryptanalyzer.anokhov.constants.OtherConstants.*;

public class SelectMode {
    View view;

    /**
      Метод создан, чтобы пользователь выбрал в каком режиме
     он хочет продоложить работать.
     */
    public void select() {
        JFrameWelcomeWindow welcome = new JFrameWelcomeWindow();
        welcome.run(start);

        switch (welcome.getView()){
            case CONSOLE -> view=new ConsolView();
            case SWING -> view=new SwingView();
        }

        MainController mainController = new MainController(view);
        Application application = new Application(mainController);
        Result result = application.run();
        application.printResult(result);
    }
}
