package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
                choose.setDialogTitle("Choose Your Strategy");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                choose.setFileFilter(filter);
                int returnValue = choose.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = choose.getSelectedFile();
                    System.out.println(selectedFile.getName());

                    try {
                        ReadFile importStrategy = new ReadFile(selectedFile);
                        String [] input = importStrategy.OpenFile();
                        coalFieldS.setText(input[0]);
                        magnetiteFieldS.setText(input[1]);
                        bauxiteFieldS.setText(input[2]);
                        chalcopyriteFieldS.setText(input[3]);
                        spodumeneFieldS.setText(input[4]);
                        aggragateFieldS.setText(input[5]);
                        csvFieldS.setText(input[6]);
                        pipeCheckS.setSelected(Boolean.parseBoolean(input[7]));
                        filterCheckS.setSelected(Boolean.parseBoolean(input[8]));
                        coalFieldW.setText(input[9]);
                        magnetiteFieldW.setText(input[10]);
                        bauxiteFieldW.setText(input[11]);
                        chalcopyriteFieldW.setText(input[12]);
                        spodumeneFieldW.setText(input[13]);
                        aggragateFieldW.setText(input[14]);
                        csvFieldW.setText(input[15]);
                        pipeCheckW.setSelected(Boolean.parseBoolean(input[16]));
                        filterCheckW.setSelected(Boolean.parseBoolean(input[17]));
                        coalFieldSF.setText(input[18]);
                        magnetiteFieldSF.setText(input[19]);
                        bauxiteFieldSF.setText(input[20]);
                        chalcopyriteFieldSF.setText(input[21]);
                        spodumeneFieldSF.setText(input[22]);
                        aggragateFieldSF.setText(input[23]);
                        csvFieldSF.setText(input[24]);
                        pipeCheckSF.setSelected(Boolean.parseBoolean(input[25]));
                        filterCheckSF.setSelected(Boolean.parseBoolean(input[26]));
                        coalFieldF.setText(input[27]);
                        magnetiteFieldF.setText(input[28]);
                        bauxiteFieldF.setText(input[29]);
                        chalcopyriteFieldF.setText(input[30]);
                        spodumeneFieldF.setText(input[31]);
                        aggragateFieldF.setText(input[32]);
                        csvFieldF.setText(input[33]);
                        pipeCheckF.setSelected(Boolean.parseBoolean(input[34]));
                        filterCheckF.setSelected(Boolean.parseBoolean(input[35]));
                    } catch (IOException d) {
                        System.out.println(d.getMessage());
                    }

                }
            }
        });

        exportStrategyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser save = new JFileChooser(new File("C:\\"));
                save.setDialogTitle("Save Your Strategy");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                save.setFileFilter(filter);
                int returnValue = save.showSaveDialog(getParent());

                try {
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File file = save.getSelectedFile();
                        BufferedWriter o = new BufferedWriter(new FileWriter(file.getAbsolutePath() + ".txt"));
                        o.write(coalFieldS.getText());
                        o.newLine();
                        o.write(magnetiteFieldS.getText());
                        o.newLine();
                        o.write(bauxiteFieldS.getText());
                        o.newLine();
                        o.write(chalcopyriteFieldS.getText());
                        o.newLine();
                        o.write(spodumeneFieldS.getText());
                        o.newLine();
                        o.write(aggragateFieldS.getText());
                        o.newLine();
                        o.write(csvFieldS.getText());
                        o.newLine();
                        o.write(Boolean.toString(pipeCheckS.isSelected()));
                        o.newLine();
                        o.write(Boolean.toString(filterCheckS.isSelected()));
                        o.newLine();
                        o.write(coalFieldW.getText());
                        o.newLine();
                        o.write(magnetiteFieldW.getText());
                        o.newLine();
                        o.write(bauxiteFieldW.getText());
                        o.newLine();
                        o.write(chalcopyriteFieldW.getText());
                        o.newLine();
                        o.write(spodumeneFieldW.getText());
                        o.newLine();
                        o.write(aggragateFieldW.getText());
                        o.newLine();
                        o.write(csvFieldW.getText());
                        o.newLine();
                        o.write(Boolean.toString(pipeCheckW.isSelected()));
                        o.newLine();
                        o.write(Boolean.toString(filterCheckW.isSelected()));
                        o.newLine();
                        o.write(coalFieldSF.getText());
                        o.newLine();
                        o.write(magnetiteFieldSF.getText());
                        o.newLine();
                        o.write(bauxiteFieldSF.getText());
                        o.newLine();
                        o.write(chalcopyriteFieldSF.getText());
                        o.newLine();
                        o.write(spodumeneFieldSF.getText());
                        o.newLine();
                        o.write(aggragateFieldSF.getText());
                        o.newLine();
                        o.write(csvFieldSF.getText());
                        o.newLine();
                        o.write(Boolean.toString(pipeCheckSF.isSelected()));
                        o.newLine();
                        o.write(Boolean.toString(filterCheckSF.isSelected()));
                        o.newLine();
                        o.write(coalFieldF.getText());
                        o.newLine();
                        o.write(magnetiteFieldF.getText());
                        o.newLine();
                        o.write(bauxiteFieldF.getText());
                        o.newLine();
                        o.write(chalcopyriteFieldF.getText());
                        o.newLine();
                        o.write(spodumeneFieldF.getText());
                        o.newLine();
                        o.write(aggragateFieldF.getText());
                        o.newLine();
                        o.write(csvFieldF.getText());
                        o.newLine();
                        o.write(Boolean.toString(pipeCheckF.isSelected()));
                        o.newLine();
                        o.write(Boolean.toString(filterCheckF.isSelected()));
                        o.close();
                    }
                } catch (IOException d) {
                    System.out.println(d.getMessage());
                }
            }
        });

        setVisible(true);
    }
}
