package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Omer on 10/7/2015.
 */
public class CoolestGUI extends JFrame{

    static Team Best;
    static Team wildBest;
    static Team BestSemi;
    static Team BestFinal;

    private JTabbedPane awesomePane;
    private JTextField coalFieldS;
    private JTextField magnetiteFieldS;
    private JTextField bauxiteFieldS;
    private JTextField chalcopyriteFieldS;
    private JTextField spodumeneFieldS;
    private JTextField aggragateFieldS;
    private JTextField csvFieldS;
    private JCheckBox pipeCheckS;
    private JCheckBox filterCheckS;
    private JTextField coalFieldW;
    private JTextField bauxiteFieldW;
    private JTextField magnetiteFieldW;
    private JTextField chalcopyriteFieldW;
    private JTextField spodumeneFieldW;
    private JTextField aggragateFieldW;
    private JTextField csvFieldW;
    private JCheckBox pipeCheckW;
    private JCheckBox filterCheckW;
    private JTextField coalFieldSF;
    private JTextField magnetiteFieldSF;
    private JTextField bauxiteFieldSF;
    private JTextField chalcopyriteFieldSF;
    private JTextField spodumeneFieldSF;
    private JTextField aggragateFieldSF;
    private JTextField csvFieldSF;
    private JCheckBox pipeCheckSF;
    private JCheckBox filterCheckSF;
    private JTextField coalFieldF;
    private JTextField magnetiteFieldF;
    private JTextField bauxiteFieldF;
    private JTextField chalcopyriteFieldF;
    private JTextField spodumeneFieldF;
    private JTextField aggragateFieldF;
    private JTextField csvFieldF;
    private JCheckBox pipeCheckF;
    private JCheckBox filterCheckF;
    private JPanel rootPanel;
    private JButton calculateButton;
    private JLabel winLabel;
    private JLabel loseLabel;
    private JButton importStrategyButton;
    private JButton exportStrategyButton;

    public CoolestGUI () throws IOException{
        super("Game Day!");

        setContentPane(rootPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Best = Main.BEST(6, Integer.parseInt(aggragateFieldS.getText()), Integer.parseInt(coalFieldS.getText()), Integer.parseInt(magnetiteFieldS.getText()), Integer.parseInt(bauxiteFieldS.getText()), Integer.parseInt(chalcopyriteFieldS.getText()), Integer.parseInt(spodumeneFieldS.getText()), Integer.parseInt(csvFieldS.getText()), filterCheckS.isSelected(), pipeCheckS.isSelected());
                wildBest = Main.BEST(1, Integer.parseInt(aggragateFieldW.getText()), Integer.parseInt(coalFieldW.getText()), Integer.parseInt(magnetiteFieldW.getText()), Integer.parseInt(bauxiteFieldW.getText()), Integer.parseInt(chalcopyriteFieldW.getText()), Integer.parseInt(spodumeneFieldW.getText()), Integer.parseInt(csvFieldW.getText()), filterCheckW.isSelected(), pipeCheckW.isSelected());
                BestSemi = Main.BEST(3, Integer.parseInt(aggragateFieldSF.getText()), Integer.parseInt(coalFieldSF.getText()), Integer.parseInt(magnetiteFieldSF.getText()), Integer.parseInt(bauxiteFieldSF.getText()), Integer.parseInt(chalcopyriteFieldSF.getText()), Integer.parseInt(spodumeneFieldSF.getText()), Integer.parseInt(csvFieldSF.getText()), filterCheckSF.isSelected(), pipeCheckSF.isSelected());
                BestFinal = Main.BEST(3, Integer.parseInt(aggragateFieldF.getText()), Integer.parseInt(coalFieldF.getText()), Integer.parseInt(magnetiteFieldF.getText()), Integer.parseInt(bauxiteFieldF.getText()), Integer.parseInt(chalcopyriteFieldF.getText()), Integer.parseInt(spodumeneFieldF.getText()), Integer.parseInt(csvFieldF.getText()), filterCheckF.isSelected(), pipeCheckF.isSelected());

                for (int i = 0; i < Main.rounds; i++) {
                    Main.gameDay(Best, wildBest, BestSemi, BestFinal);
                }

                winLabel.setText("We won: " + Main.won/Main.rounds*100 + "%");
                loseLabel.setText("We lost: " + Main.lost/Main.rounds*100 + "%");
            }
        });

        importStrategyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser choose = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                choose.setFileFilter(filter);
                int returnValue = choose.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = choose.getSelectedFile();
                    System.out.println(selectedFile.getName());

                    try {
                        ReadFile importStrategy = new ReadFile(selectedFile);
                        String [] impStrat = importStrategy.OpenFile();
                        handleImport(impStrat);
                    } catch (IOException d) {
                        System.out.println(d.getMessage());
                    }

                }
            }
        });

        setVisible(true);
    }

    public static void handleImport(String [] input) {
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }
}
