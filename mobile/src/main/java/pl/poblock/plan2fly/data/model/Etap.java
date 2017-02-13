package pl.poblock.plan2fly.data.model;

/**
 * Created by krzysztof.poblocki on 2017-02-06.
 */

public class Etap {
    private String skad;
    private String dokad;
    private String linia;

    public Etap(String skad, String dokad, String linia) {
        this.skad = skad;
        this.dokad = dokad;
        this.linia = linia;
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

    public String getLinia() {
        return linia;
    }

    public void setLinia(String linia) {
        this.linia = linia;
    }


    @Override
    public String toString() {
        return "Etap{" +
                "skad='" + skad + '\'' +
                ", dokad='" + dokad + '\'' +
                ", linia='" + linia + '\'' +
                '}';
    }
}
