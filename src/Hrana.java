public class Hrana {
    private Vrchol startVrchol;
    private Vrchol koncVrchol;
    private int cena;

    public Hrana(Vrchol start, Vrchol koniec, int cena) {
        this.startVrchol = start;
        this.koncVrchol = koniec;
        this.cena = cena;
    }

    public void vypisHranu() {
        System.out.println(
                "{" + this.startVrchol.getId() + ", " + this.koncVrchol.getId() + ", " + this.getCena() + "}");
    }

    public Vrchol getStartVrchol() {
        return startVrchol;
    }

    public Vrchol getKoncVrchol() {
        return koncVrchol;
    }

    public int getCena() {
        return cena;
    }

}
