
public class Main {
    public static void main(String[] args) throws Exception {
        Graf graf = new Graf("ATG_DAT/Trvania1.txt", "ATG_DAT/Hrany1.txt");
        graf.vypisVrcholy();
        graf.cpm();
    }
}
