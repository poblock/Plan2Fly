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
    private int ofert;

    public Query(String skad, String dokad, int miesiac, int rok, boolean czyWDC, int iloscOfert) {
        this.skad = skad.substring(skad.length()-3);
        this.dokad = dokad.substring(dokad.length()-3);
        this.skadFull = skad.substring(0, skad.length()-3);
        this.dokadFull = dokad.substring(0, dokad.length()-3);
        this.miesiac = miesiac;
        this.rok = rok;
        this.czyWDC = czyWDC;
        this.ofert = iloscOfert;
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

    public int getOfert() {
        return ofert;
    }

    public void setOfert(int ofert) {
        this.ofert = ofert;
    }
}
