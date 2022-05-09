import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Graf {
    private TreeMap<Integer, Vrchol> vrcholy;
    private ArrayList<Vrchol> monoton;
    private int[] p;
    private int[] k;
    private int[] z;

    public Graf() {
        this.vrcholy = new TreeMap<>();
    }

    public void vyberGraf() {
        System.out.print("Vyber graf (zadaj cislo len do 4): ");
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        s.close();
        switch (i) {
            case 1: {
                this.nacitajGraf("ATG_DAT/Trvania.txt", "ATG_DAT/Hrany.txt");
                break;
            }
            case 2: {
                this.nacitajGraf("ATG_DAT/ACYKL/CPM_midi.tim", "ATG_DAT/ACYKL/CPM_midi.hrn");
                break;
            }
            case 3: {
                this.nacitajGraf("ATG_DAT/ACYKL/CPM_mini.tim", "ATG_DAT/ACYKL/CPM_mini.hrn");
                break;
            }
            case 4: {
                this.nacitajGraf("ATG_DAT/ACYKL/CPM_stred.tim", "ATG_DAT/ACYKL/CPM_stred.hrn");
                break;
            }
            default: {
                System.out.println("Len do 4");
                break;
            }
        }
        System.out.println();
        this.vypisVrcholy();
    }

    private void nacitajGraf(String vrcholy, String hrany) {
        try {
            File vrcholySubor = new File(vrcholy);
            Scanner s = new Scanner(vrcholySubor);
            int riadok = 1;
            while (s.hasNext()) {
                int cas = s.nextInt();
                if (this.vrcholy.get(riadok) == null) {
                    this.vrcholy.put(riadok, new Vrchol(riadok, cas));
                }
                riadok++;
            }
            s.close();

            File hranySubor = new File(hrany);
            Scanner sc = new Scanner(hranySubor);
            while (sc.hasNext()) {
                int startVrchol = sc.nextInt();
                int koncVrchol = sc.nextInt();
                sc.skip(".*");
                this.vrcholy.get(startVrchol).pridajVychadzajucuHranu(koncVrchol);
                this.vrcholy.get(koncVrchol).pridajVchadzajucuHranu(startVrchol);
            }
            sc.close();
        } catch (IOException ex) {
            System.err.println("Nepodarilo sa nacitat subor...");
        }

    }

    public void cpm() {
        int celkoveTrvanie = cpmZaciatky();
        this.cpmKonce(celkoveTrvanie);
        this.vypisCpm(celkoveTrvanie);
        this.kritickeCinnosti();
    }

    private void monotonneOcislovanie() {
        this.monoton = new ArrayList<>();
        this.monoton.add(null);

        for (Map.Entry<Integer, Vrchol> vrchols : this.vrcholy.entrySet()) {
            if (vrchols.getValue().getInDeg() == 0) {
                this.monoton.add(vrchols.getValue());
            }
        }

        int i = 1;
        while (this.monoton.size() != this.vrcholy.size() + 1) {
            int v = this.monoton.get(i).getId();
            for (Hrana hrana : this.vrcholy.get(v).getVystupne()) {
                this.vrcholy.get(hrana.getKoncVrchol()).odcitajInDeg();
                if (this.vrcholy.get(hrana.getKoncVrchol()).getInDeg() == 0) {
                    this.monoton.add(this.vrcholy.get(hrana.getKoncVrchol()));
                }
            }
            i++;
        }
    }

    private void inicializujCPM() {
        this.p = new int[this.vrcholy.size() + 1];
        this.z = new int[this.vrcholy.size() + 1];
        this.k = new int[this.vrcholy.size() + 1];

        this.monotonneOcislovanie();

        for (int i = 1; i < this.vrcholy.size() + 1; i++) {
            this.p[this.monoton.get(i).getId()] = this.monoton.get(i).getTrvanie();
        }
    }

    private int cpmZaciatky() {
        this.inicializujCPM();

        int[] trvanie = new int[this.vrcholy.size() + 1];
        int celkoveTrvanie = 0;

        for (int i = 1; i < this.vrcholy.size() + 1; i++) {
            this.z[i] = 0;
        }

        for (int i = 1; i < this.monoton.size(); i++) {
            Vrchol v = this.monoton.get(i);

            for (int j = 0; j < v.getVystupne().size(); j++) {
                Hrana w = v.getVystupne().get(j);
                if (z[w.getKoncVrchol()] < z[v.getId()] + p[v.getId()]) {
                    z[w.getKoncVrchol()] = z[v.getId()] + p[v.getId()];
                    trvanie[w.getKoncVrchol()] = v.getId();
                }
            }
        }

        for (int i = 1; i < this.monoton.size(); i++) {
            int porovnanie = z[this.monoton.get(i).getId()] + p[this.monoton.get(i).getId()];
            if (porovnanie > celkoveTrvanie) {
                celkoveTrvanie = porovnanie;
            }
        }

        return celkoveTrvanie;
    }

    private void cpmKonce(int celkoveTrvanie) {
        int[] trvanie = new int[this.vrcholy.size() + 1];

        for (int i = 1; i < this.vrcholy.size() + 1; i++) {
            this.k[i] = celkoveTrvanie;
        }

        for (int i = this.monoton.size() - 1; i > 0; i--) {

            Vrchol v = this.monoton.get(i);

            for (int j = 0; j < v.getVystupne().size(); j++) {
                Hrana w = v.getVystupne().get(j); // vrchol vo vystupnej hviezde

                if (k[v.getId()] > (k[w.getKoncVrchol()] - p[w.getKoncVrchol()])) {
                    k[v.getId()] = k[w.getKoncVrchol()] - p[w.getKoncVrchol()];
                    trvanie[v.getId()] = w.getKoncVrchol();
                }
            }
        }
    }

    private void kritickeCinnosti() {
        ArrayList<Integer> kritickeCinnosti = new ArrayList<>();
        for (int i = 1; i < this.vrcholy.size() + 1; i++) {
            int v = this.vrcholy.get(i).getId();
            int rezerva = this.k[v] - this.z[v] - this.p[v];
            if (rezerva == 0) {
                kritickeCinnosti.add(v);
            }
        }

        System.out.print("Kriticke cinnosti su:");
        for (int i : kritickeCinnosti) {
            System.out.print(", " + i);
        }
        System.out.println();
    }

    private void vypisCpm(int celkoveTrvanie) {
        System.out.println("Zaciatky:");
        for (int i = 1; i < this.monoton.size(); i++) {
            System.out.printf("v: %2d, z[v]: %4d%n", this.monoton.get(i).getId(),
                    z[this.monoton.get(i).getId()]);
        }
        System.out.println();
        System.out.println();

        System.out.println("Konce:");
        for (int i = this.monoton.size() - 1; i > 0; i--) {
            System.out.printf("v: %2d, k[v]: %4d%n", this.monoton.get(i).getId(),
                    k[this.monoton.get(i).getId()]);
        }

        System.out.println("Celkove trvanie projektu: " + celkoveTrvanie);
    }

    private void vypisVrcholy() {
        for (Map.Entry<Integer, Vrchol> vrchols : this.vrcholy.entrySet()) {
            System.out.printf("Vrchol %2d, p: %3d, InDeg: %2d, OutDeg: %2d%n", vrchols.getValue().getId(),
                    vrchols.getValue().getTrvanie(), vrchols.getValue().getInDeg(), vrchols.getValue().getOutDeg());
        }
        System.out.println();
    }
}
