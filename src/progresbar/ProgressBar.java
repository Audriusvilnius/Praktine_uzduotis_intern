package progresbar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import java.awt.*;

// Klase turetu suveikti po submitButton paspaudimo Main. clase
public class ProgressBar extends JPanel {
    private final JProgressBar progressBar;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 100;

    public ProgressBar() {
        progressBar = new JProgressBar();
        progressBar.setMinimum(MIN_VALUE);
        progressBar.setMaximum(MAX_VALUE);
        add(progressBar);
    }

    public void updateProgress(int value) {
        progressBar.setStringPainted(true); // Show a percentage string
        progressBar.setValue(value);
       //  progressBar.setValue(progress); // reik paimt is Main.class

    }
    public static void main(String[] args) {
        final ProgressBar progressBar = new ProgressBar();
        progressBar.setPreferredSize(new Dimension(300, 30));
        JFrame frame = new JFrame("Calculation Progress");
//        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(progressBar);
        frame.pack();
        frame.setVisible(true);


        // Update the progress bar
        for (int i = 0; i <= 100; i++) {
            final int percent = i;
//            frame.setSize(300, 60);
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        progressBar.updateProgress(percent);
                    }
                });
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 100) {
                System.exit(0);
            }
        }
    }
}
