package progres;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

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
        progressBar.setValue(value);
    }

    public static void main(String[] args) {
        final ProgressBar example = new ProgressBar();
        JFrame frame = new JFrame("Progress Bar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(example);
        frame.pack();
        frame.setVisible(true);

        // Update the progress bar
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            final int percent = i;
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        example.updateProgress(percent);
                    }
                });
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
