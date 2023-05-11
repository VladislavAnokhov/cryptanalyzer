package com.javarush.cryptanalyzer.anokhov.jframeWindows;

import com.javarush.cryptanalyzer.anokhov.constants.AlfaBet;
import com.javarush.cryptanalyzer.anokhov.entity.FileWay;
import com.javarush.cryptanalyzer.anokhov.entity.Mode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.javarush.cryptanalyzer.anokhov.constants.ApplicationCommunication.*;
import static com.javarush.cryptanalyzer.anokhov.constants.ApplicationCommunication.breaker;
import static com.javarush.cryptanalyzer.anokhov.constants.FileWays.read;
import static com.javarush.cryptanalyzer.anokhov.constants.FileWays.write;
import static com.javarush.cryptanalyzer.anokhov.constants.OtherConstants.errorNameofWindow;


public class JFrameApplicationWindow extends JFrame implements Window {
    private JFrame frame;
    private JPanel centerPanel;
    private String state;
    private int key;
    private JTextField nameWayRead,nameWayWrite,nameKey;
    private JLabel labelWayRead, labelWayWrite;
    private JRadioButton radioButtonRead,radioButtonWrite, radioButtonKey;
    private Mode modeStatus;
    JButton modeButton;

    /** Метод, который запускает новый графический интерфейс.
     * Графический интерфейс строится под ту функцию, которая необходима
     * пользователю, добавляя необходимый функционал
     */
    @Override
    public void run(String mode) {
        state=mode;
        frame = new JFrame(mode);
        frame.setBounds(750, 300, 700, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

      if (!mode.equals(breaker)){
          centerPanel = new JPanel(new GridLayout(3, 3));  //создание поля 3 на 3 для равномерного распределения

          nameKey = new JTextField("");
          nameKey.setFont(new Font("", Font.PLAIN, 14));
      }
      else
      {
          centerPanel = new JPanel(new GridLayout(2, 2));
      }
        JPanel panelNorth = new JPanel(new FlowLayout());

        JLabel labelWhatToDO = new JLabel(setWayForFiles);

        if (mode.equals(encrypt)) {
            labelWayRead = new JLabel(encryptFile);
            labelWayWrite = new JLabel(encryptedFile);

            radioButtonKey = new JRadioButton(genericKey);
            radioButtonKey.addActionListener(new ListenKey());
        } else {
            labelWayRead = new JLabel(decryptFile);
            labelWayWrite = new JLabel(decryptedFile);
        }
        JLabel labelKey = new JLabel(secretKey);
        panelNorth.add(labelWhatToDO);

        radioButtonRead = new JRadioButton(useDefaultFile);
        radioButtonRead.addActionListener(new ListenInFile());

        radioButtonWrite = new JRadioButton(useDefaultFile);
        radioButtonWrite.addActionListener(new ListenOutFile());


        nameWayRead = new JTextField("");
        nameWayRead.setFont(new Font("", Font.PLAIN, 14));  // первый

        nameWayWrite = new JTextField("");
        nameWayWrite.setFont(new Font("", Font.PLAIN, 14)); // второй


        modeButton = new JButton(mode);
        modeButton.addActionListener(new ListenButton());
        frame.add(modeButton, BorderLayout.PAGE_END);

        centerPanel.add(labelWayRead);
        centerPanel.add(nameWayRead);
        centerPanel.add(radioButtonRead);

        centerPanel.add(labelWayWrite);
        centerPanel.add(nameWayWrite);
        centerPanel.add(radioButtonWrite);

        if (!mode.equals(breaker)) {
            centerPanel.add(labelKey);
            centerPanel.add(nameKey);}

        if (mode.equals(encrypt)) {
            centerPanel.add(radioButtonKey);
        }

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    /**
     * Метод, в котором основной поток приостанавливается,
     * пока пользователь не укажет все параметры для
     * дальнейшей работы
     * @return
     */
    @Override
    public Mode getInformation() {
        synchronized (this) {
            while (modeStatus == null) {
                try {
                    wait(1000);
                } catch (InterruptedException ex) {
                }
            }
            notify();
        }
        return modeStatus;
    }

    /** listener для указания дефолного пути первого файла(ввода).
     * Если не выбран - то поле становится пустым и ждет
     * другого значения.
     */
    class  ListenInFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FileWay fileWay = new FileWay();
            if (!radioButtonRead.isSelected()) {
                nameWayRead.setText("");
            }
            else
                nameWayRead.setText(fileWay.getFileWay(state,read));
            }
        }
    /** listener для указания дефолного пути второго файла(вывода).
     * Если не выбран - то поле становится пустым и ждет
     * другого значения.
     */
    class ListenOutFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FileWay fileWay = new FileWay();
            if (!radioButtonWrite.isSelected()) {
                nameWayWrite.setText("");
            }
            else
                nameWayWrite.setText(fileWay.getFileWay(state,write));
        }
    }
    /** listener для указания рандомного ключа.
     * Если не выбран - то поле становится пустым и ждет
     * другого значения.
     */
    class  ListenKey implements ActionListener {
        @Override
        public  void actionPerformed(ActionEvent e) {
            if (!radioButtonKey.isSelected()) {
                nameKey.setText("");
            }
            else if (radioButtonKey.isSelected()){
                key = 1 + (int) (Math.random() * (AlfaBet.alfaBet.size() - 1));
                nameKey.setText(Integer.toString(key));}
        }
    }


    /** listener для кнопки. Сделан метод для
     * дальнейшего перехода программы, а также в нем
     * заложена проверка "пустых" полей
     */
    class ListenButton implements ActionListener {
        @Override
        public  void actionPerformed(ActionEvent e) {

            if (!state.equals(breaker)) {
                if (nameWayRead.getText().equalsIgnoreCase("") || nameWayWrite.getText().equals("") || nameKey.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, errorOfWindow, errorNameofWindow, JOptionPane.PLAIN_MESSAGE);}
                else {
                modeStatus = new Mode(state, nameWayRead.getText(), nameWayWrite.getText(), Integer.parseInt(nameKey.getText()));
                    frame.dispose();}
            }
            else if (nameWayRead.getText().equalsIgnoreCase("") || nameWayWrite.getText().equals("")){
                JOptionPane.showMessageDialog(null, errorOfWindow, errorNameofWindow, JOptionPane.PLAIN_MESSAGE);
            }
            else {
                modeStatus = new Mode(state, nameWayRead.getText(), nameWayWrite.getText());
                frame.dispose();}

        }
    }
}
