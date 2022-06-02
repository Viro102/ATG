public class Hrana {
    private int startVrchol;
    private int koncVrchol;
    private int cena;

    public Hrana(int start, int koniec, int cena) {
        this.startVrchol = start;
        this.koncVrchol = koniec;
        this.cena = cena;
    }

    public Hrana(int start, int koniec) {
        this.startVrchol = start;
        this.koncVrchol = koniec;
    }

    public int getCena() {
        return this.cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public void vypisHranu() {
        System.out.printf("{ %d, %d, c: %d}%n", this.startVrchol, this.koncVrchol, this.cena);
    }

    public int getStartVrchol() {
        return startVrchol;
    }

    public int getKoncVrchol() {
        return koncVrchol;
    }
}
