
public class Main {
    public static void main(String[] args) throws Exception {
        Graf graf = new Graf("ATG_DAT/Trvania.txt", "ATG_DAT/Hrany.txt");
        // graf.vypisVrcholy();
        graf.CPM();
    }
}
