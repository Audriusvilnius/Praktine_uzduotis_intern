package progresbar;

import com.sun.tools.javac.Main;

import javax.swing.*;


public class ProgressBar extends JFrame {
    JProgressBar jb;

    int i = 0;

    ProgressBar() {
        jb = new JProgressBar(0, 100);
        jb.setBounds(20, 20, 260, 30);
        jb.setValue(0);
        jb.setStringPainted(true);
        add(jb);
        setSize(300, 100);
        setLayout(null);
    }

    public void iterate() {
        while (i <= 100) {
            jb.setValue(i);
            i = i + 1;
            try {
                Thread.sleep(150);
            } catch (Exception ignored) {
            }
        }
    }

    public static void main(String[] args) {
        ProgressBar m = new ProgressBar();
        m.setVisible(true);
        m.iterate();
        System.exit(0);
    }
}