package pl.poblock.plan2fly.data.model;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class Lot {
    private String skad;
    private String dokad;
    private String linia;
    private String dataWylotu;
    private String godzinaWylotu;
    private String dataPrzylotu;
    private String godzinaPrzylotu;
    private String czasLotu;
    private String cena;

    public Lot(String skad, String dokad, String linia, String dataWylotu, String godzinaWylotu, String dataPrzylotu, String godzinaPrzylotu, String czasLotu, String cena) {
        this.skad = skad;
        this.dokad = dokad;
        this.linia = linia;
        this.dataWylotu = dataWylotu;
        this.godzinaWylotu = godzinaWylotu;
        this.dataPrzylotu = dataPrzylotu;
        this.godzinaPrzylotu = godzinaPrzylotu;
        this.czasLotu = czasLotu;
        this.cena = cena;
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

    public String getDataWylotu() {
        return dataWylotu;
    }

    public void setDataWylotu(String dataWylotu) {
        this.dataWylotu = dataWylotu;
    }

    public String getGodzinaWylotu() {
        return godzinaWylotu;
    }

    public void setGodzinaWylotu(String godzinaWylotu) {
        this.godzinaWylotu = godzinaWylotu;
    }

    public String getDataPrzylotu() {
        return dataPrzylotu;
    }

    public void setDataPrzylotu(String dataPrzylotu) {
        this.dataPrzylotu = dataPrzylotu;
    }

    public String getGodzinaPrzylotu() {
        return godzinaPrzylotu;
    }

    public void setGodzinaPrzylotu(String godzinaPrzylotu) {
        this.godzinaPrzylotu = godzinaPrzylotu;
    }

    public String getCzasLotu() {
        return czasLotu;
    }

    public void setCzasLotu(String czasLotu) {
        this.czasLotu = czasLotu;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "skad='" + skad + '\'' +
                ", dokad='" + dokad + '\'' +
                ", linia='" + linia + '\'' +
                ", dataWylotu='" + dataWylotu + '\'' +
                ", godzinaWylotu='" + godzinaWylotu + '\'' +
                ", dataPrzylotu='" + dataPrzylotu + '\'' +
                ", godzinaPrzylotu='" + godzinaPrzylotu + '\'' +
                ", czasLotu='" + czasLotu + '\'' +
                ", cena='" + cena + '\'' +
                '}';
    }
}
