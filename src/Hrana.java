public class Hrana {
    private int startVrchol;
    private int koncVrchol;

    public Hrana(int start, int koniec) {
        this.startVrchol = start;
        this.koncVrchol = koniec;
    }

    public void vypisHranu() {
        System.out.println(
                "{" + this.startVrchol + ", " + this.koncVrchol + "}");
    }

    public int getStartVrchol() {
        return startVrchol;
    }

    public int getKoncVrchol() {
        return koncVrchol;
    }
}
