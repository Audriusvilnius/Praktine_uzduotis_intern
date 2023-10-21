/*
Užduotis darbinantis Java junior pozicijai:
Sukurti programą, skirtą skaičiaus išskaidymui pirminiais dauginamaisiais.
Startavus programą, turi būti atidaromas langas, kuriame turi būti galima įvesti tris skaičius: nuo kurio skaičiaus pradėti skaičiuoti, iki kurio skaičiaus skaičiuoti ir kas kiek didinti skaičių
(pvz.: jei įvedami skaičiai tokie: 100, 200, 26, tai programa turi išskaidyti dauginamaisiais šiuos skaičius: 100, 126, 152, 178). Lange turi būti mygtukai „Pradėti“ ir „Baigti“ bei progressbar‘as skaičiavimo eigos demonstravimui (procentine išraiška, pagal aukščiau pateiktą pavyzdį, po kiekvieno skaičiaus išskaidymo, progressbar‘o reikšmė turi būti didinama 25%). Paspaudus mygtuką „Pradėti“ turi būti pradedamas skaičiavimas pagal įvestas reikšmes, paspaudus „Baigti“, jis turi būti nutraukiamas (pradėtas skaidyti skaičius turi būti užbaigtas).
Kiekvieno skaičiaus skaidymas turi užtrukti ne mažiau nei 500 ms, t.y. jei išskaidymas atliekamas greičiau nei 0.5 s, tuomet programa turi laukti ir kitą skaičiuoti pradėti tik praėjus 0.5 s, nuo prieš tai esančio skaičiaus skaidymo pradžios.
Kiekvienas išskaidytas skaičius turi būti išsaugomas tekstiniame faile. Failo struktūra (pavyzdys):
2012.03.20 10:00:00.000 Skaičiavimo pradžia. Naudojami skaičiai: 100, 200, 26.
2012.03.20 10:00:00.010 100=2*2*5*5
2012.03.20 10:00:00:520 126=2*3*3*7
2012.03.20 10:00:01:030 152=2*2*2*19
2012.03.20 10:00:01:540 178=2*89
2012.03.20 10:00:02:050 Skaičiavimo pabaiga.
Skaičius skaidant pirminiais dauginamaisiais programa turi išlikti pilnai interaktyvi, t.y. turi būti galima įvesti naujus skaičius skaidymui (tai neturi įtakoti dabar atliekamų skaidymų eilės). Paspaudus mygtuką „Pradėti“, programa turi pradėti skaidyti naujai įvestus skaičius (nutraukiant prieš tai atliekamus skaičiavimus). Jei skaičiavimo metu programa yra uždaroma, tuomet turi būti užbaigtas skaidyti einamas skaičius, jis turi būti išsaugomas tekstiniame faile ir tik tada programa turi būti uždaryta. Tekstinis failas su pertraukimu atrodytų taip:
2012.03.20 10:00:00.000 Skaičiavimo pradžia. Naudojami skaičiai: 100, 200, 26.
2012.03.20 10:00:00.010 100=2*2*5*5
2012.03.20 10:00:00:520 126=2*3*3*7
2012.03.20 10:00:00.800 Skaičiavimo pradžia. Naudojami skaičiai: 100, 200, 26. – šioje vietoje mygtukas „Pradėti“ buvo paspaustas dar kartą nepakeitus pradinių skaičių.
2012.03.20 10:00:00.850 100=2*2*5*5
2012.03.20 10:00:01:360 126=2*3*3*7
2012.03.20 10:00:01:880 152=2*2*2*19
2012.03.20 10:00:02:400 178=2*89
2012.03.20 10:00:02:910 Skaičiavimo pabaiga.
Papildoma užduotis:
Papildyti formą nauju atvaizdavimo lauku, kuriame būtų pateikiama tokia informacija:
·      Kai atliekamas skaičiavimas: „Skaidomas skaičius: 100“;
·      Kai skaičiavimai baigti: „Skaidymas baigtas. Rezultatai faile rezultatai.txt“
Pastaba: „100“ – čia turi būti pateikiamas konkretus skaidomas skaičius einamu momentu.
„rezultatai.txt“ – čia turi būti pateikiamas failo pavadinimas, kuriame išsaugoti skaidymo rezultatai (failo pavadinimas gali būti kitoks).
 */

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
import javax.swing.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) throws IOException {


        JFrame frame = new JFrame("Registration Form");
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
                    int valueFrom = Integer.parseInt(from);
                    int valueTo = Integer.parseInt(to);
                    int valueSteps = Integer.parseInt(step);
                } catch (NumberFormatException ex) {
                    resultLabelFrom.setText("Invalid input From: " + from);
                    resultLabelTo.setText("Invalid input To: " + to);
                    resultLabelSteps.setText("Invalid input Steps: " + step);
                }
                panel.add(resultLabelFrom);
                panel.add(resultLabelTo);
                panel.add(resultLabelSteps);
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


        inputData();
        createInterval();

        int primeNumberQty = 0;
        System.out.println(Arrays.toString(data));
        String dataArray = Arrays.toString(data);

        if (!dataArray.isEmpty()) {
            dataArray = dataArray.substring(1, dataArray.length() - 1);
        }
        String start = timeNow() + " Skaičiavimo pradžia. Naudojami skaičiai: " + dataArray;
        try {
            FileWriter fileWriter = new FileWriter("rezultatai.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(start);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (int i = 0; i < data.length; i++) {
            primeNumberQty = primeNumberQty(data[i]);
            primeArray = new int[primeNumberQty + 1];
            primeArray[0] = data[i];
            primenumberArray(primeArray);
            System.out.println(Arrays.toString(primeArray));
            dataSave(primeArray, timeNow());
        }

        String end = timeNow() + " Skaičiavimo pabaiga.";
        try {
            FileWriter fileWriter = new FileWriter("rezultatai.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(end);
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

    private static void createInterval() {
        int interval = (data_array[1] - data_array[0]) / data_array[2];
        int progressBar = data_array[2] * 100 / (data_array[1] - data_array[0]);
        data = new int[interval + 1];
        data[0] = data_array[0];
        for (int i = 1; i <= interval; i++) {
            data[i] = data[i - 1] + data_array[2];
        }

        int qty = 0;
        int proc = 0;
        System.out.println();
        System.out.println(proc + "% - " + data[0]);
        for (int i = 1; i < interval + 1; i++) {
            try {
                data[i] = data[i - 1] + data_array[2];
                proc += progressBar;
                {
                    int[] number = new int[qty];
                }
                if (data[i] == data_array[1]) {
                    proc = 100;
                }
                // delay 0.5 seconds
                sleep(500);
                System.out.println(proc + "% - " + data[i]);
            } catch (InterruptedException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
    }

    private static int[] primenumberArray(int[] primeArray) {
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
        return primeArray;
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

    static public int[] data_array = new int[3];
    static public int[] data;
    static public int[] primeArray;

    private static void inputData() {
        Scanner inp = new Scanner(System.in);
        System.out.println("Iveskite tris sveikus skaicius. Intervlas nuo iki ir intervalo žingsnis.");
        System.out.print("Intevalas nuo: ");
        int check = 0;
        //String data = "100";
        String data = inp.next();
        if (isNumeric(data)) data_array[0] = Integer.parseInt(data);
        else {
            System.out.println("Ivestas ne sveikus skaicius");
            inputData();
        }
        System.out.print("Intevalas iki: ");
        while (check != 1) {
            //data = "200";
            data = inp.next();
            if (isNumeric(data)) {
                data_array[1] = Integer.parseInt(data);
                if (data_array[1] <= data_array[0]) {
                    System.out.print("Intevalas iki negali buti mazesnis uz reiksme iki, pakartotinai iveskite iki reiksme: ");
                } else check = 1;
            } else {
                System.out.println(" Ivestas ne sveikus skaicius, iveskite pakartotinai");
            }
        }

        System.out.print("Žingsnis: ");
        while (check != 0) {
            //data = "26";
            data = inp.next();
            if (isNumeric(data)) {
                data_array[2] = Integer.parseInt(data);
                if ((data_array[1] - data_array[0]) >= data_array[2] && (data_array[1] - data_array[0]) != 0) {
                    check = 0;
                } else {
                    System.out.print("Intevalo zingsnis negali but didesnis uz nuo iki skirtuma, pakartotinai iveskite zinksni: ");
                }
            } else
                System.out.println(" Ivestas ne sveikus skaicius, iveskite pakartotinai");
        }
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}