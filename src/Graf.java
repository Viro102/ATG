import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Graf {

    private TreeMap<Integer, Vrchol> vrcholy;

    public Graf(String vrcholy, String hrany) throws IOException {
        this.vrcholy = new TreeMap<>();

        try {
            if (nacitajGraf(vrcholy, hrany) == 1) {
                throw new IOException();
            }
        } catch (IOException ex) {
            System.err.println("Nepodarilo sa nacitat subor...");
        }
    }

    public int nacitajGraf(String vrcholy, String hrany) {
        try {
            File vrcholySubor = new File(vrcholy);
            Scanner s = new Scanner(vrcholySubor);
            while (s.hasNext()) {
                int vrchol = s.nextInt();
                int trvanie = s.nextInt();
                if (this.vrcholy.get(vrchol) == null) {
                    this.vrcholy.put(vrchol, new Vrchol(vrchol, trvanie));
                }
            }
            s.close();
            File hranySubor = new File(hrany);
            Scanner sc = new Scanner(hranySubor);
            while (sc.hasNext()) {
                int startVrchol = sc.nextInt();
                int koncVrchol = sc.nextInt();
                this.vrcholy.get(startVrchol).pridajVychadzajucuHranu(koncVrchol);
                this.vrcholy.get(koncVrchol).pridajVchadzajucuHranu(startVrchol);
            }
            sc.close();
            return 0;
        } catch (IOException ex) {
            return 1;
        }

    }

    public void vypisVrcholy() {
        for (Map.Entry<Integer, Vrchol> vrchols : this.vrcholy.entrySet()) {
            System.out.printf("Vrchol %d, p: %d, InDeg: %d, OutDeg: %d%n", vrchols.getValue().getId(),
                    vrchols.getValue().getTrvanie(), vrchols.getValue().getInDeg(), vrchols.getValue().getOutDeg());

        }
    }

    public ArrayList<Vrchol> monotonneOcislovanie() {
        ArrayList<Vrchol> P = new ArrayList<>();
        P.add(null);
        for (Map.Entry<Integer, Vrchol> vrchols : this.vrcholy.entrySet()) {
            if (vrchols.getValue().getInDeg() == 0) {
                P.add(vrchols.getValue());
            }
        }
        int i = 1;
        while (P.size() != this.vrcholy.size() + 1) {
            int v = P.get(i).getId();
            for (int vrchol : this.vrcholy.get(v).getVystupne()) {
                this.vrcholy.get(vrchol).odcitajInDeg();
                if (this.vrcholy.get(vrchol).getInDeg() == 0) {
                    P.add(this.vrcholy.get(vrchol));
                }
            }
            i++;
        }

        return P;
    }

    public void cpm() {
        int[] p = new int[this.vrcholy.size() + 1];
        int[] z = new int[this.vrcholy.size() + 1];
        int[] trvanie = new int[this.vrcholy.size() + 1];

        ArrayList<Vrchol> monoton = this.monotonneOcislovanie();

        for (int i = 1; i < this.vrcholy.size() + 1; i++) {
            p[monoton.get(i).getId()] = monoton.get(i).getTrvanie();
            z[i] = 0;
        }

        for (int i = 1; i < monoton.size(); i++) {
            Vrchol v = monoton.get(i);
            trvanie[v.getId()] = z[v.getId()] + p[v.getId()];
            for (int j = 0; j < v.getVystupne().size(); j++) {
                z[v.getVystupne().get(j)] = trvanie[v.getId()];
            }
        }

        for (int i = 1; i < monoton.size(); i++) {
            System.out.printf("v: %d, najskor mozny zaciatok: %d%n", monoton.get(i).getId(),
                    trvanie[monoton.get(i).getId()]);
        }

        int celkoveTrvanie = trvanie[monoton.get(monoton.size() - 1).getId()];
        System.out.println("Celkove trvanie projektu: " + celkoveTrvanie);

    }

}
