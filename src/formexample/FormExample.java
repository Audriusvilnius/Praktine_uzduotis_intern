package formexample;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Form Example");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel label = new JLabel("Enter an integer:");
        JTextField textField = new JTextField(10);
        panel.add(label);
        panel.add(textField);

        JButton button = new JButton("Convert");
        panel.add(button);

        JLabel resultLabel = new JLabel();
        panel.add(resultLabel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                try {
                    int value = Integer.parseInt(text);
                    resultLabel.setText("The integer value is: " + value);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input: " + text);
                }
            }
        });

        frame.setVisible(true);
    }
}