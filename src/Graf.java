import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graf {

    private ArrayList<Hrana> hrany;
    private ArrayList<Vrchol> vrcholy;

    public Graf(String subor) throws IOException {
        this.hrany = new ArrayList<>();
        this.vrcholy = new ArrayList<>();

        try {
            if (nacitajGraf(subor) == 1) {
                throw new IOException();
            }
        } catch (IOException ex) {
            System.err.println("Nepodarilo sa nacitat subor...");
        }
    }

    public int nacitajGraf(String subor) {
        try {
            File graf = new File(subor);
            Scanner s = new Scanner(graf);
            while (s.hasNextLine()) {
                Vrchol startVrchol = new Vrchol(s.nextInt());
                Vrchol koncVrchol = new Vrchol(s.nextInt());
                int cena = s.nextInt();
                Hrana hrana = new Hrana(startVrchol, koncVrchol, cena);
                this.hrany.add(hrana);
            }
            s.close();
            return 0;
        } catch (IOException ex) {
            return 1;
        }

    }

    public void vypisHrany() {
        for (Hrana hrana : this.hrany) {
            hrana.vypisHranu();
        }
    }

    public void vypisVrcholy() {
        for (Vrchol vrchol : this.vrcholy) {
            System.out.printf("%d, ", vrchol.getId());
        }
    }

    public void setVrcholy() {
        int[] idVrchola = new int[this.vrcholy.size()];
        Hrana[] incidentneHrany = new Hrana[this.hrany.size()];

    }
}
