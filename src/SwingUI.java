import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SwingUI {
    public static JTextField textFieldFileName;
    public static JTextField textFieldPattern;
    public static JTextField textFieldRepetitions;
    public static JTextField textFieldTextLength;
    public static JTextField textFieldMaximumPatternLength;
    public static JTextField textFieldMinimumPatternLength;
    public static JTextField textFieldAlphabet;
    public static JTextArea textAreaOutput;
    public static JCheckBox[] checkBoxes = new JCheckBox[Main.numberOfAlgorithms];

    public static void run() {
        /*
        Color menuColor = new Color(60, 63, 65);
        Color borderColor = new Color(49, 51, 53);
        Color inputAreaColor = new Color(43, 43, 43);
        Color fontColor = new Color(169, 183, 198);
         */
        Color menuColor = new Color(242, 242, 242);
        Color borderColor = new Color(212, 212, 212);
        Color inputAreaColor = new Color(255, 255, 255);
        Color fontColor = new Color(0, 0, 0);

        JFrame frame = new JFrame("String Search Algorithm Comparison");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1350, 800);
        frame.setBackground(menuColor);
        //frame.setUndecorated(true);
        //GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);

        JPanel panel = new JPanel();
        panel.setSize(frame.getSize());
        panel.setBackground(menuColor);
        panel.setLayout(null);

        JButton buttonMeasureTime = new JButton("Measure Time");
        buttonMeasureTime.setBounds(10, 440, 200, 20);
        buttonMeasureTime.setBackground(inputAreaColor);
        buttonMeasureTime.setForeground(fontColor);
        buttonMeasureTime.setBorder(BorderFactory.createLineBorder(borderColor));
        buttonMeasureTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEnabledAlgorithms();

                char text[] = Main.readFile(textFieldFileName.getText());
                char pattern[];
                char alphabet[];

                if (text == null) {
                    alphabet = textFieldAlphabet.getText().toCharArray();
                    text = Main.generateText(Integer.parseInt(textFieldTextLength.getText()), alphabet);
                } else {
                    alphabet = Main.getAlphabet(text);
                }

                if (textFieldPattern.getText().equals("")) {
                    pattern = Main.generateText(Integer.parseInt(textFieldMaximumPatternLength.getText()), alphabet);
                } else {
                    pattern = textFieldPattern.getText().toCharArray();
                }

                int highestCharacter = Main.getHighestCharacter(Main.concatenateArrays(Main.getAlphabet(pattern), Main.getAlphabet(text)));
                textAreaOutput.setText(Main.repeatedTimeMeasurement(Integer.parseInt(textFieldMinimumPatternLength.getText()), pattern, text, Integer.parseInt(textFieldRepetitions.getText()), highestCharacter));

                if (Integer.parseInt(textFieldMinimumPatternLength.getText()) <= 0) {
                    appendFindings(pattern, text, highestCharacter);
                }
            }
        });

        JButton buttonCountOperations = new JButton("Count Operations");
        buttonCountOperations.setBounds(10, 470, 200, 20);
        buttonCountOperations.setBackground(inputAreaColor);
        buttonCountOperations.setForeground(fontColor);
        buttonCountOperations.setBorder(BorderFactory.createLineBorder(borderColor));
        buttonCountOperations.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEnabledAlgorithms();

                char text[] = Main.readFile(textFieldFileName.getText());
                char pattern[];
                char alphabet[];

                if (text == null) {
                    alphabet = textFieldAlphabet.getText().toCharArray();
                    text = Main.generateText(Integer.parseInt(textFieldTextLength.getText()), alphabet);
                } else {
                    alphabet = Main.getAlphabet(text);
                }

                if (textFieldPattern.getText().equals("")) {
                    pattern = Main.generateText(Integer.parseInt(textFieldMaximumPatternLength.getText()), alphabet);
                } else {
                    pattern = textFieldPattern.getText().toCharArray();
                }

                int highestCharacter = Main.getHighestCharacter(Main.concatenateArrays(Main.getAlphabet(pattern), Main.getAlphabet(text)));
                textAreaOutput.setText(Main.countingOfAllOperations(Integer.parseInt(textFieldMinimumPatternLength.getText()), pattern, text, highestCharacter));

                if (Integer.parseInt(textFieldMinimumPatternLength.getText()) <= 0) {
                    appendFindings(pattern, text, highestCharacter);
                }
            }
        });

        JLabel labelFileName = new JLabel("Filename");
        labelFileName.setBounds(11, 15, 200, 20);
        labelFileName.setForeground(fontColor);
        textFieldFileName = new JTextField("C:\\Users\\user\\Documents\\Test.txt");
        textFieldFileName.setBounds(10, 40, 200, 20);
        textFieldFileName.setBackground(inputAreaColor);
        textFieldFileName.setForeground(fontColor);
        textFieldFileName.setBorder(BorderFactory.createLineBorder(borderColor));
        JLabel labelPattern = new JLabel("Pattern");
        labelPattern.setBounds(11, 75, 200, 20);
        labelPattern.setForeground(fontColor);
        textFieldPattern = new JTextField("Any word");
        textFieldPattern.setBounds(10, 100, 200, 20);
        textFieldPattern.setBackground(inputAreaColor);
        textFieldPattern.setForeground(fontColor);
        textFieldPattern.setBorder(BorderFactory.createLineBorder(borderColor));
        JLabel labelRepetitions = new JLabel("Repetitions");
        labelRepetitions.setBounds(11, 135, 200, 20);
        labelRepetitions.setForeground(fontColor);
        textFieldRepetitions = new JTextField("10");
        textFieldRepetitions.setBounds(10, 160, 200, 20);
        textFieldRepetitions.setBackground(inputAreaColor);
        textFieldRepetitions.setForeground(fontColor);
        textFieldRepetitions.setBorder(BorderFactory.createLineBorder(borderColor));
        JLabel labelTextLength = new JLabel("Text Length");
        labelTextLength.setBounds(11, 195, 200, 20);
        labelTextLength.setForeground(fontColor);
        textFieldTextLength = new JTextField("1000000");
        textFieldTextLength.setBounds(10, 220, 200, 20);
        textFieldTextLength.setBackground(inputAreaColor);
        textFieldTextLength.setForeground(fontColor);
        textFieldTextLength.setBorder(BorderFactory.createLineBorder(borderColor));
        JLabel labelMaximumPatternLength = new JLabel("Maximum Pattern Length");
        labelMaximumPatternLength.setBounds(11, 255, 200, 20);
        labelMaximumPatternLength.setForeground(fontColor);
        textFieldMaximumPatternLength = new JTextField("8");
        textFieldMaximumPatternLength.setBounds(10, 280, 200, 20);
        textFieldMaximumPatternLength.setBackground(inputAreaColor);
        textFieldMaximumPatternLength.setForeground(fontColor);
        textFieldMaximumPatternLength.setBorder(BorderFactory.createLineBorder(borderColor));
        JLabel labelMinimumPatternLength = new JLabel("Minimum Pattern Length");
        labelMinimumPatternLength.setBounds(11, 315, 200, 20);
        labelMinimumPatternLength.setForeground(fontColor);
        textFieldMinimumPatternLength = new JTextField("1");
        textFieldMinimumPatternLength.setBounds(10, 340, 200, 20);
        textFieldMinimumPatternLength.setBackground(inputAreaColor);
        textFieldMinimumPatternLength.setForeground(fontColor);
        textFieldMinimumPatternLength.setBorder(BorderFactory.createLineBorder(borderColor));
        JLabel labelAlphabet = new JLabel("Alphabet");
        labelAlphabet.setBounds(11, 375, 200, 20);
        labelAlphabet.setForeground(fontColor);
        textFieldAlphabet = new JTextField("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.!?-:'");
        textFieldAlphabet.setBounds(10, 400, 200, 20);
        textFieldAlphabet.setBackground(inputAreaColor);
        textFieldAlphabet.setForeground(fontColor);
        textFieldAlphabet.setBorder(BorderFactory.createLineBorder(borderColor));

        textAreaOutput = new JTextArea("Results will be shown here:");
        textAreaOutput.setBounds(220, 15, 1100, frame.getHeight() - 65);
        textAreaOutput.setBackground(inputAreaColor);
        textAreaOutput.setForeground(fontColor);
        textAreaOutput.setBorder(BorderFactory.createLineBorder(borderColor));
        textAreaOutput.setEditable(false);

        for (int a = 0; a < Main.numberOfAlgorithms; ++a) {
            checkBoxes[a] = new JCheckBox(Main.algorithms[a], true);
            if (a < Main.numberOfAlgorithms / 2) {
                checkBoxes[a].setBounds(10, 520 + a * 30, 95, 20);
            } else {
                checkBoxes[a].setBounds(115, 520 + (a - Main.numberOfAlgorithms / 2) * 30, 95, 20);
            }
            checkBoxes[a].setBackground(inputAreaColor);
            checkBoxes[a].setForeground(fontColor);
            checkBoxes[a].setBorder(BorderFactory.createLineBorder(borderColor));
            panel.add(checkBoxes[a]);
        }

        panel.add(buttonMeasureTime);
        panel.add(buttonCountOperations);
        panel.add(textFieldFileName);
        panel.add(labelFileName);
        panel.add(textFieldPattern);
        panel.add(labelPattern);
        panel.add(textFieldRepetitions);
        panel.add(labelRepetitions);
        panel.add(textFieldMaximumPatternLength);
        panel.add(labelMaximumPatternLength);
        panel.add(textFieldMinimumPatternLength);
        panel.add(labelMinimumPatternLength);
        panel.add(textFieldTextLength);
        panel.add(labelTextLength);
        panel.add(textFieldAlphabet);
        panel.add(labelAlphabet);
        panel.add(textAreaOutput);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void setEnabledAlgorithms() {
        for (int a = 0; a < Main.numberOfAlgorithms; ++a) {
            Main.enabledAlgorithms[a] = checkBoxes[a].isSelected();
        }
    }

    public static void appendFindings(char[] pattern, char[] text, int highestCharacter) {
        textAreaOutput.append("\nIndexes Of Found Patterns By BF:\n");
        List<Integer> indexes = BF.measureTime(pattern, text);
        indexes.remove(0);
        for (int a = 0; a < indexes.size(); ++a) {
            textAreaOutput.append(indexes.get(a) + " ");
        }
        textAreaOutput.append("\nIndexes Of Found Patterns By BM:\n");
        indexes = BM.measureTime(pattern, text, highestCharacter);
        indexes.remove(0);
        for (int a = 0; a < indexes.size(); ++a) {
            textAreaOutput.append(indexes.get(a) + " ");
        }
        textAreaOutput.append("\nIndexes Of Found Patterns By BNDM:\n");
        indexes = BNDM.measureTime(pattern, text, highestCharacter);
        indexes.remove(0);
        for (int a = 0; a < indexes.size(); ++a) {
            textAreaOutput.append(indexes.get(a) + " ");
        }
        textAreaOutput.append("\nIndexes Of Found Patterns By HP:\n");
        indexes = HP.measureTime(pattern, text, highestCharacter);
        indexes.remove(0);
        for (int a = 0; a < indexes.size(); ++a) {
            textAreaOutput.append(indexes.get(a) + " ");
        }
        textAreaOutput.append("\nIndexes Of Found Patterns By KPMSS:\n");
        indexes = KPMSS.measureTime(pattern, text, highestCharacter);
        indexes.remove(0);
        for (int a = 0; a < indexes.size(); ++a) {
            textAreaOutput.append(indexes.get(a) + " ");
        }
        textAreaOutput.append("\nIndexes Of Found Patterns By MS:\n");
        indexes = MS.measureTime(pattern, text, highestCharacter);
        indexes.remove(0);
        for (int a = 0; a < indexes.size(); ++a) {
            textAreaOutput.append(indexes.get(a) + " ");
        }
        textAreaOutput.append("\nIndexes Of Found Patterns By QS:\n");
        indexes = QS.measureTime(pattern, text, highestCharacter);
        indexes.remove(0);
        for (int a = 0; a < indexes.size(); ++a) {
            textAreaOutput.append(indexes.get(a) + " ");
        }
        textAreaOutput.append("\nIndexes Of Found Patterns By RT:\n");
        indexes = RT.measureTime(pattern, text, highestCharacter);
        indexes.remove(0);
        for (int a = 0; a < indexes.size(); ++a) {
            textAreaOutput.append(indexes.get(a) + " ");
        }
        textAreaOutput.append("\nIndexes Of Found Patterns By SMT:\n");
        indexes = SMT.measureTime(pattern, text, highestCharacter);
        indexes.remove(0);
        for (int a = 0; a < indexes.size(); ++a) {
            textAreaOutput.append(indexes.get(a) + " ");
        }
        textAreaOutput.append("\nIndexes Of Found Patterns By SO:\n");
        indexes = SO.measureTime(pattern, text, highestCharacter);
        indexes.remove(0);
        for (int a = 0; a < indexes.size(); ++a) {
            textAreaOutput.append(indexes.get(a) + " ");
        }
        textAreaOutput.append("\nIndexes Of Found Patterns By SS:\n");
        indexes = SS.measureTime(pattern, text, highestCharacter);
        indexes.remove(0);
        for (int a = 0; a < indexes.size(); ++a) {
            textAreaOutput.append(indexes.get(a) + " ");
        }
    }
}