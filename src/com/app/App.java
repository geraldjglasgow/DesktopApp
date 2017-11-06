package com.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import getdata.Get_Info;
import com.analyze.*;

public class App {
    public JPanel panelMain;
    private JButton analyzeButton;
    private JButton button2;
    private JButton button3;
    public JProgressBar progressBar1;
    private JLabel label0;
    private JLabel label1;
    private String home = "~/";
    private JFileChooser fc;
    private String file="";


    public App(){
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File(home));
                fc.setDialogTitle("test");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.setAcceptAllFileFilterUsed(false);
                fc.showOpenDialog(panelMain);
                file = fc.getSelectedFile().toString();
                home = fc.getSelectedFile().getParent();
                label0.setText(file);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Get_Info data = new Get_Info();
                data.conn(progressBar1, file);
                progressBar1.setValue(100);
            }
        });
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = file;
                GetFiles p = new GetFiles(path);
                Stats s = new Stats(p.getFilelist());
                label1.setText("Matches: " + s.getMatches());
            }
        });
    }
}
