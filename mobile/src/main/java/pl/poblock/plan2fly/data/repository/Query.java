package pl.poblock.plan2fly.data.repository;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class Query {
    private String skad;
    private String dokad;
    private int miesiac;
    private int rok;

    public Query(String skad, String dokad, int miesiac, int rok) {
        this.skad = skad.substring(skad.length()-3);
        this.dokad = dokad.substring(dokad.length()-3);
        this.miesiac = miesiac;
        this.rok = rok;
    }

    public String getSkad() {
        return skad;
    }

    public void setSkad(String skad) {
        this.skad = skad;
    }

    public String getDokad() {
        return dokad;
    }

    public void setDokad(String dokad) {
        this.dokad = dokad;
    }

    public int getMiesiac() {
        return miesiac;
    }

    public void setMiesiac(int miesiac) {
        this.miesiac = miesiac;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }
}
