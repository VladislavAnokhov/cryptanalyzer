package com.javarush.cryptanalyzer.anokhov.controller;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JButton;

import static com.javarush.cryptanalyzer.anokhov.constants.ApplicationCommunication.*;
import static com.javarush.cryptanalyzer.anokhov.constants.ApplicationCommunication.breaker;

public class JFrameWindowModeController extends JFrame {
    static Lock lock = new ReentrantLock();

    private JFrame frame;
    private JLabel label;
  static private String modeStatus ;

    /**класс и методы созданы для получения
     * от пользователя режима работы программы
     */
    public JFrameWindowModeController() {
        frame = new JFrame("Application");
        frame.setBounds(750, 300, 700, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        label = new JLabel(welcome,SwingConstants.CENTER); // текст

        JButton encryptButton = new JButton(encrypt);
        encryptButton.addActionListener(new ButtonEventManager());
        JButton decipherButton = new JButton(decipher);
        decipherButton.addActionListener(new ButtonEventManager());
        JButton breakerButton = new JButton(breaker);
        breakerButton.addActionListener(new ButtonEventManager());

        JPanel buttons = new JPanel();
        buttons.add(encryptButton);
        buttons.add(decipherButton);
        buttons.add(breakerButton);

        JPanel text = new JPanel();
        text.add(label);

        frame.add(label);
        frame.add(buttons,BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public String getModeStatus (){
        synchronized (this){
            while (modeStatus==null){
                try{
                    wait(1000);
                }
                catch (InterruptedException ex){
                }
            } notify();
        }
        return modeStatus;
    }

    class ButtonEventManager implements ActionListener {
        @Override
        public synchronized void  actionPerformed(ActionEvent e) {
            synchronized (this) {
                while (modeStatus != null) {
                    try {
                        wait(1000);
                    } catch (InterruptedException ew) {
                    }
                }

            }    modeStatus= e.getActionCommand();
            notify();
            frame.dispose();

        }
    }
    }
