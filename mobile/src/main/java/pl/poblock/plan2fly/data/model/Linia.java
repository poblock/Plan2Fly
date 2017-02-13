package pl.poblock.plan2fly.data.model;

import java.util.List;

/**
 * Created by krzysztof.poblocki on 2017-02-06.
 */

public class Linia {
    private String skad;
    private String dokad;
    private List<Etap> etapy;

    public Linia(String skad, String dokad, List<Etap> etapy) {
        this.skad = skad;
        this.dokad = dokad;
        this.etapy = etapy;
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

    public List<Etap> getEtapy() {
        return etapy;
    }

    public void setEtapy(List<Etap> etapy) {
        this.etapy = etapy;
    }


    @Override
    public String toString() {
        return "Linia{" +
                "skad='" + skad + '\'' +
                ", dokad='" + dokad + '\'' +
                ", etapy=" + etapy +
                '}';
    }
}
