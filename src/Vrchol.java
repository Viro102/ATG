import java.util.ArrayList;

public class Vrchol {
    private int id;
    private int inDeg;
    private int outDeg;
    private ArrayList<Hrana> incidentneHrany;

    public Vrchol(int id) {
        this.inDeg = 0;
        this.outDeg = 0;
        this.incidentneHrany = new ArrayList<>();
        this.id = id;
    }

    public void pridajIncidentnuHranu(Hrana hrana) {
        this.incidentneHrany.add(hrana);
    }

    public void vypisIncidentneHrany() {
        for (Hrana hrana : this.incidentneHrany) {
            hrana.vypisHranu();
        }
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

    public void setOutDeg(int i) {
        this.outDeg = i;
    }

    public void setInDeg(int i) {
        this.inDeg = i;
    }

}
