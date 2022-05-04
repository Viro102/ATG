import java.util.ArrayList;
import java.util.List;

public class Vrchol {
    private int id;
    private int inDeg;
    private int outDeg;
    private int trvanie;
    private ArrayList<Integer> vychadzajuceHrany;
    private ArrayList<Integer> vchadzajuceHrany;

    public Vrchol(int id, int trvanie) {
        this.inDeg = 0;
        this.outDeg = 0;
        this.vchadzajuceHrany = new ArrayList<>();
        this.vychadzajuceHrany = new ArrayList<>();
        this.id = id;
        this.trvanie = trvanie;
    }

    public void pridajVchadzajucuHranu(int hrana) {
        this.vchadzajuceHrany.add(hrana);
        this.inDeg++;
    }

    public void pridajVychadzajucuHranu(int hrana) {
        this.vychadzajuceHrany.add(hrana);
        this.outDeg++;
    }

    public void vypisHrany(String typ) {
        if (typ.equals("vchadzajuce")) {
            for (int hrana : this.vchadzajuceHrany) {
                System.out.printf("%d", hrana);
            }
        } else if (typ.equals("vychadzajuce")) {
            for (int hrana : this.vychadzajuceHrany) {
                System.out.printf("%d", hrana);
            }
        }

    }

    public List<Integer> getVystupne() {
        return this.vychadzajuceHrany;
    }

    public List<Integer> getVstupne() {
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

    public void setOutDeg(int i) {
        this.outDeg = i;
    }

    public void setInDeg(int i) {
        this.inDeg = i;
    }

    public void odcitajInDeg() {
        this.inDeg--;
    }

}
