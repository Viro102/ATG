import java.util.ArrayList;
import java.util.List;

public class Vrchol {
    private int id;
    private int inDeg;
    private int outDeg;
    private int trvanie;
    private ArrayList<Hrana> vychadzajuceHrany;
    private ArrayList<Hrana> vchadzajuceHrany;

    public Vrchol(int id, int trvanie) {
        this.inDeg = 0;
        this.outDeg = 0;
        this.vchadzajuceHrany = new ArrayList<>();
        this.vychadzajuceHrany = new ArrayList<>();
        this.id = id;
        this.trvanie = trvanie;
    }

    public Vrchol(int id) {
        this.inDeg = 0;
        this.outDeg = 0;
        this.vchadzajuceHrany = new ArrayList<>();
        this.vychadzajuceHrany = new ArrayList<>();
        this.id = id;
    }

    public void pridajVchadzajucuHranu(int hrana, int cena) {
        this.vchadzajuceHrany.add(new Hrana(hrana, this.id, cena));
        this.inDeg++;
    }

    public void pridajVchadzajucuHranu(int hrana) {
        this.vchadzajuceHrany.add(new Hrana(hrana, this.id));
        this.inDeg++;
    }

    public void pridajVychadzajucuHranu(int hrana, int cena) {
        this.vychadzajuceHrany.add(new Hrana(this.id, hrana, cena));
        this.outDeg++;
    }

    public void pridajVychadzajucuHranu(int hrana) {
        this.vychadzajuceHrany.add(new Hrana(this.id, hrana));
        this.outDeg++;
    }

    public void vypisHrany(String typ) {
        if (typ.equals("vchadzajuce")) {
            for (Hrana hrana : this.vchadzajuceHrany) {
                System.out.printf("%d", hrana.getStartVrchol());
            }
        } else if (typ.equals("vychadzajuce")) {
            for (Hrana hrana : this.vychadzajuceHrany) {
                System.out.printf("%d", hrana.getKoncVrchol());
            }
        }

    }

    public List<Hrana> getVystupne() {
        return this.vychadzajuceHrany;
    }

    public List<Hrana> getVstupne() {
        return this.vchadzajuceHrany;
    }

    public int getId() {
        return this.id;
    }

    public int getOutDeg() {
        return this.outDeg;
    }

    public int getInDeg() {
        return this.inDeg;
    }

    public int getTrvanie() {
        return this.trvanie;
    }

    public void odcitajInDeg() {
        this.inDeg--;
    }

}
