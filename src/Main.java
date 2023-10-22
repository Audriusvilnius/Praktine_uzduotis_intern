import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import static java.lang.Thread.sleep;
import java.awt.event.*;




public class Main {

    static public int[] data_array = new int[3];
    static public int[] data;
    static public int[] primeArray;
    static public int progressBar = 0;


    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Prime number calculator");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel fromLabel = new JLabel("From:");
        JTextField fromText = new JTextField(10);
        panel.add(fromLabel);
        panel.add(fromText);

        JLabel toLabel = new JLabel("To:");
        JTextField toText = new JTextField(10);
        panel.add(toLabel);
        panel.add(toText);

        JLabel stepLabel = new JLabel("Steps:");
        JTextField stepText = new JTextField(10);
        panel.add(stepLabel);
        panel.add(stepText);

        JButton submitButton = new JButton("Submit");
        JButton abortButton = new JButton("Abort");

        JLabel resultLabelFrom = new JLabel();
        JLabel resultLabelTo = new JLabel();
        JLabel resultLabelSteps = new JLabel();

        submitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String from = fromText.getText();
                String to = toText.getText();
                String step = stepText.getText();

                System.out.println(from);
                System.out.println(to);
                System.out.println(step);

                try {
                    frame.setSize(700, 200);
                    resultLabelFrom.setText("");
                } catch (NumberFormatException ex) {
                    resultLabelFrom.setText("Invalid input From: " + from);
                    panel.add(resultLabelFrom);
                }
                try {
                    frame.setSize(700, 200);
                    resultLabelTo.setText("");
                } catch (NumberFormatException ex) {
                    resultLabelTo.setText("Invalid input To: " + to);
                    panel.add(resultLabelTo);
                }
                try {
                    frame.setSize(700, 200);
                    resultLabelSteps.setText("");
                } catch (NumberFormatException ex) {
                    resultLabelSteps.setText("Invalid input Steps: " + step);
                    panel.add(resultLabelSteps);
                }
                inputData(from , to , step);
                createInterval(from, to, step);

        int primeNumberQty;
        System.out.println(Arrays.toString(data));
        String dataArray = Arrays.toString(data);

        if (!dataArray.isEmpty()) {
            dataArray = dataArray.substring(1, dataArray.length() - 1);
        }
        String start = timeNow() + " Skaičiavimo pradžia. Naudojami skaičiai: " + dataArray;
        writrToFile(start);

        for (int datum : data) {
            primeNumberQty = primeNumberQty(datum);
            primeArray = new int[primeNumberQty + 1];
            primeArray[0] = datum;
            primeNumberArray(primeArray);
            System.out.println(Arrays.toString(primeArray));
            try {
                dataSave(primeArray, timeNow());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        String end = timeNow() + " Skaičiavimo pabaiga.";
        writrToFile(end);

            }
        });

        panel.add(submitButton);
        panel.add(abortButton);

        abortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    private static void writrToFile(String start) {
        try {
            FileWriter fileWriter = new FileWriter("rezultatai.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(start);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static String timeNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return now.format(formatter);
    }

    private static void dataSave(int[] primeArray, String time) throws IOException {
        StringBuilder str = new StringBuilder();
        String fileName = "rezultatai.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

        StringBuilder multiplication = new StringBuilder(primeArray[0] + "=");

        for (int i = 1; i < primeArray.length; i++) {
            multiplication.append(primeArray[i]).append("*");
        }
        if (!multiplication.isEmpty()) {
            multiplication = new StringBuilder(multiplication.substring(0, multiplication.length() - 1));
        }

        str.append(time).append(" ").append(multiplication).append("\n");
        writer.append(str);
        writer.close();
    }

    private static void createInterval(String from, String to, String step) {
        int valueTo =Integer.parseInt(to);
        int valueFrom =Integer.parseInt(from);
        int valueStep =Integer.parseInt(step);
        int interval = (valueTo - valueFrom) / valueStep;
        progressBar = (valueStep * 100) / (valueTo - valueFrom);
        data = new int[interval + 1];
        data[0] = data_array[0];
        for (int i = 1; i <= interval; i++) {
            data[i] = data[i - 1] + data_array[2];
        }
        int proc = 0;
        System.out.println();
        System.out.println(proc + "% - " + data[0]);
        for (int i = 1; i < interval + 1; i++) {
            try {
                data[i] = data[i - 1] + data_array[2];
                proc += progressBar;
                if (data[i] == data_array[1]) {
                    proc = 100;
                }
                // delay 0.5 seconds
                sleep(0);
                System.out.println(proc + "% - " + data[i]);
            } catch (InterruptedException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
    }

    private static void primeNumberArray(int[] primeArray) {
        Main.primeArray = primeArray;
        int value = primeArray[0];
        for (int i = 2, y = 1; i <= value; i++) {
            String startDif = timeNow();
            startDif = startDif.substring(20);
            int start = Integer.parseInt(startDif);
            //System.out.println(start);

            if ((value % i) == 0) {
                String endtDif = timeNow();
                endtDif = endtDif.substring(20);
                int end = Integer.parseInt(endtDif);
                //System.out.println(end);
                if (start > end) end = end + 1;
                if (end - start < 500) {
                    delay();
                }
                value = value / i;
                primeArray[y] = i;
                y++;
                i = 1;
            }
        }
    }

    private static void delay() {
        try {
            // delay 0.5 seconds
            sleep(500);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    private static int primeNumberQty(int count) {
        int qty = 0;
        for (int i = 2; i <= count; i++) {
            if ((count % i) == 0) {
                count = count / i;
                qty++;
                i = 1;
            }
        }
/*
    primary number qty
        for (int i = 2; i <= sqrt(count); i++) {
            if ((count % i) == 0) qty++;
       }
*/
        return qty;
    }

    private static void inputData(String from, String to, String step) {
        //Scanner inp = new Scanner(System.in);
        System.out.println("Iveskite tris sveikus skaicius. Intervlas nuo iki ir intervalo žingsnis.");
        System.out.print("Intevalas nuo: ");
        int check = 0;
        //String data = from;
        //String data = inp.next();
        if (isNumeric(from)) data_array[0] = Integer.parseInt(from);
        else {
            System.out.println("Ivestas ne sveikus skaicius");
            // inputData(from, to, step);
        }
        System.out.print("Intevalas iki: ");
        while (check != 1) {
            //data = to;
            //data = inp.next();
            if (isNumeric(to)) {
                data_array[1] = Integer.parseInt(to);
                if (data_array[1] <= data_array[0]) {
                    System.out.print("Intevalas iki negali buti mazesnis uz reiksme iki, pakartotinai iveskite iki reiksme: ");
                } else check = 1;
            } else {
                System.out.println(" Ivestas ne sveikus skaicius, iveskite pakartotinai");
            }
        }

        System.out.print("Žingsnis: ");
        while (check != 0) {
            //data = step;
            //data = inp.next();
            if (isNumeric(step)) {
                data_array[2] = Integer.parseInt(step);
                if ((data_array[1] - data_array[0]) >= data_array[2] && (data_array[1] - data_array[0]) != 0) {
                    check = 0;
                } else {
                    System.out.print("Intevalo zingsnis negali but didesnis uz nuo iki skirtuma, pakartotinai iveskite zinksni: ");
                }
            } else System.out.println(" Ivestas ne sveikus skaicius, iveskite pakartotinai");
        }
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }





}