package com.javarush.cryptanalyzer.anokhov.jframeWindows;

import com.javarush.cryptanalyzer.anokhov.entity.Mode;
import com.javarush.cryptanalyzer.anokhov.entity.Result;
import javafx.scene.Parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.javarush.cryptanalyzer.anokhov.constants.ApplicationCommunication.secretKey;
import static com.javarush.cryptanalyzer.anokhov.constants.OtherConstants.finalWindows;
import static com.javarush.cryptanalyzer.anokhov.constants.ResultConstants.HAVEDONE;

public class JFrameResultWindow extends JFrame {
    JFrame frame;

    /**
     * класс и метод созданы для выведения результата на экран
     * @param result
     */
    public void run (Result result){
        frame = new JFrame(finalWindows);
        frame.setBounds(750, 300, 700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel labelkey = new JLabel(secretKey);
        JLabel keyIs = new JLabel(String.valueOf(result.getKey()));
          JButton buttonResult = new JButton(HAVEDONE);
          buttonResult.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  frame.dispose();
              }
          });

        JPanel panelKey = new JPanel(new FlowLayout());
        panelKey.add(labelkey);
        panelKey.add(keyIs);

        JTextArea area = new JTextArea(20,57);
        area.setText(result.getStringBuilder().toString());

        area.setFont(new Font("Serif", Font.ITALIC, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

       JScrollPane scrollPane = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
               JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       JPanel panel = new JPanel();
       panel.setLayout(new FlowLayout());
       panel.add(scrollPane);

        frame.add(panel);
        frame.add(panelKey,BorderLayout.NORTH);
        frame.add(buttonResult,BorderLayout.PAGE_END);

        frame.setVisible(true);
    }
}
