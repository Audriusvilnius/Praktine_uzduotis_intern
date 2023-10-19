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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        inputData();
        createInterval();

        StringBuilder str = new StringBuilder();
        String fileName = "rezultatai.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        for (int datum : data) {
            writer.newLine();
            str.append(String.valueOf(datum));
            writer.newLine();
        }
            writer.append(str);
//        writer.append(str.toString());

        writer.close();
    }

    private static void createInterval() {
        int interval = (data_array[1] - data_array[0]) / data_array[2];
        int progressBar = data_array[2] * 100 / (data_array[1] - data_array[0]);
        int proc = 0;
        data = new int[interval + 1];
        data[0] = data_array[0];
        System.out.println(proc + "% - " + data[0]);
        for (int i = 1; i < interval + 1; i++) {
            try {
                data[i] = data[i - 1] + data_array[2];
                proc += progressBar;
                if (data[i] == data_array[1]) {
                    proc = 100;
                }
                // delay 0.5 seconds
                Thread.sleep(500);
                System.out.println(proc + "% - " + data[i]);
            } catch (InterruptedException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
    }

    static public int[] data_array = new int[3];
    static public int[] data;

    private static void inputData() {
        Scanner inp = new Scanner(System.in);
        System.out.println("Iveskite tris sveikus skaicius. Intervlas nuo iki ir intervalo žingsnis.");
        System.out.print("Intevalas nuo: ");
        int check = 0;
        String data = inp.next();
        if (isNumeric(data)) data_array[0] = Integer.parseInt(data);
        else {
            System.out.println(" Ivestas ne sveikus skaicius");
            inputData();
        }
        System.out.print("Intevalas iki: ");
        while (check != 1) {
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