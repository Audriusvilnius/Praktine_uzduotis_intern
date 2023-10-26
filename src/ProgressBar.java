import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressBar extends JFrame {
    private JProgressBar bar;
    private int progress;

    public ProgressBar() {
        setTitle("Calculate Progress");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);

        bar = new JProgressBar(0, 100);
        bar.setSize(300, 100);
        bar.setBounds(200, 200, 260, 30);
        bar.setValue(0);
        bar.setStringPainted(true);

        add(bar);

        // You can add code here to perform an action when the progress is complete.
        // Update the progress value as needed
        Timer timer = new Timer(500, new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                if (progress >= 100) {
                    ((Timer) e.getSource()).stop();
                    // You can add code here to perform an action when the progress is complete.
                } else {
                    progress += Main.progress; // Update the progress value as needed
                    bar.setValue(progress);
                }
            }
        });
        timer.start();
    }

    public static void main() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProgressBar bar = new ProgressBar();
                bar.setVisible(true);
            }
        });
    }
}