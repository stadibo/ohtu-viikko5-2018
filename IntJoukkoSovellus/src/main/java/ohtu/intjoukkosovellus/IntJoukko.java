package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenMaara;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti liian pieni"); //heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kasvatuskoko liian pieni"); //heitin vaan jotain :D
        }
        joukko = new int[kapasiteetti];
        for (int i = 0; i < joukko.length; i++) {
            joukko[i] = 0;
        }
        alkioidenMaara = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public int[] getJoukko() {
        return joukko;
    }

    public boolean lisaa(int luku) {
        if (!onJoukossa(luku) || alkioidenMaara == 0) {
            onkoKasvatettava();
            joukko[alkioidenMaara] = luku;
            alkioidenMaara++;
            return true;
        }
        return false;
    }

    private void onkoKasvatettava() {
        if (alkioidenMaara == joukko.length) {

            int[] taulukkoOld = new int[joukko.length];
            kopioiTaulukko(joukko, taulukkoOld);
            joukko = new int[alkioidenMaara + kasvatuskoko];
            kopioiTaulukko(taulukkoOld, joukko);
        }
    }

    public boolean onJoukossa(int luku) {
        for (int i = 0; i < alkioidenMaara; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int indeksi = haeArvonIndeksi(luku);
        if (indeksi != -1) {
            siirraVasemmalle(indeksi);
            return true;
        }
        return false;
    }

    private void siirraVasemmalle(int indeksi) {
        for (int j = indeksi; j < alkioidenMaara - 1; j++) {
            joukko[j] = joukko[j + 1];
        }
        alkioidenMaara--;
    }

    private int haeArvonIndeksi(int luku) {
        for (int i = 0; i < alkioidenMaara; i++) {
            if (luku == joukko[i]) {
                return i;
            }
        }
        return -1;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int getAlkioidenMaara() {
        return alkioidenMaara;
    }

    @Override
    public String toString() {
        String alkiot = "";
        if (alkioidenMaara > 0) {
            if (alkioidenMaara > 1) {
                for (int i = 0; i < alkioidenMaara - 1; i++) {
                    alkiot += joukko[i] + ", ";
                }
            }
            alkiot += joukko[alkioidenMaara - 1];
        }
        return "{" + alkiot + "}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenMaara];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = joukko[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        for (int i = 0; i < a.getJoukko().length; i++) {
            for (int j = 0; j < b.getJoukko().length; j++) {
                if (a.getJoukko()[i] == b.getJoukko()[j]) {
                    y.lisaa(b.getJoukko()[j]);
                }
            }
        }
        return y;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        for (int i = 0; i < a.getJoukko().length; i++) {
            z.lisaa(a.getJoukko()[i]);
        }
        for (int i = 0; i < b.getJoukko().length; i++) {
            z.poista(i);
        }
        return z;
    }
}