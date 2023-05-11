package com.javarush.cryptanalyzer.anokhov.jframeWindows;

import com.javarush.cryptanalyzer.anokhov.entity.Mode;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static com.javarush.cryptanalyzer.anokhov.constants.ApplicationCommunication.mainWelcome;
import static com.javarush.cryptanalyzer.anokhov.constants.OtherConstants.CONSOLE;
import static com.javarush.cryptanalyzer.anokhov.constants.OtherConstants.SWING;

public class JFrameWelcomeWindow extends JFrame implements Window {
    private String view ;
    private JFrame frame;
    @Override
    /** класс и метод созданы для приветствия и
     * получения информации от пользователя, какой
     * вид программы он хочет видеть
     */
    public void run(String mode) {
        frame = new JFrame(mode);
        frame.setBounds(750, 300, 700, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton buttonConsole = new JButton(CONSOLE);
        buttonConsole.addActionListener(new ListenButton());
        JButton buttonSwing = new JButton(SWING);
        buttonSwing.addActionListener(new ListenButton());

        JPanel panel = new JPanel();
        panel.add(buttonConsole);
        panel.add(buttonSwing);


        JTextArea welcomeText = new JTextArea();
        welcomeText.setText(mainWelcome);
        welcomeText.setFont(new Font("", Font.BOLD, 14));
        welcomeText.setLineWrap(true);
        welcomeText.setWrapStyleWord(true);
        frame.add(welcomeText,BorderLayout.NORTH);
        frame.add(panel,BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    class ListenButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            view = e.getActionCommand();
            frame.dispose();

        }
    }

    public String getView (){
        synchronized (this) {
            while (view == null) {
                try {
                    wait(1000);
                } catch (InterruptedException ex) {
                }
            }
            notify();
        }
        return view;
    }

    @Override
    public Mode getInformation() {
        return null;
    }

}
