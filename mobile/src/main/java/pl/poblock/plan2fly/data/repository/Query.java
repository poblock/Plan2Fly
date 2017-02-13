package pl.poblock.plan2fly.data.repository;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class Query {
    private String skadFull;
    private String skad;
    private String dokad;
    private String dokadFull;
    private int miesiac;
    private int rok;
    private boolean czyWDC;

    public Query(String skad, String dokad, int miesiac, int rok, boolean czyWDC) {
        this.skad = skad.substring(skad.length()-3);
        this.dokad = dokad.substring(dokad.length()-3);
        this.skadFull = skad.substring(0, skad.length()-3);
        this.dokadFull = dokad.substring(0, dokad.length()-3);
        this.miesiac = miesiac;
        this.rok = rok;
        this.czyWDC = czyWDC;
    }

    public String getSkad() {
        return skad;
    }

    public String getDokad() {
        return dokad;
    }

    public int getMiesiac() {
        return miesiac;
    }

    public int getRok() {
        return rok;
    }

    public String getSkadFull() {
        return skadFull;
    }

    public String getDokadFull() {
        return dokadFull;
    }

    public boolean isCzyWDC() {
        return czyWDC;
    }

    public void setCzyWDC(boolean czyWDC) {
        this.czyWDC = czyWDC;
    }

    public int czyWDC() {
        return czyWDC ? 1 : 0;
    }
}
