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
    private String skadFull;
    private String dokadFull;

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

    public String getDokadFull() {
        return dokadFull;
    }

    public void setDokadFull(String dokadFull) {
        this.dokadFull = dokadFull;
    }

    public String getSkadFull() {
        return skadFull;
    }

    public void setSkadFull(String skadFull) {
        this.skadFull = skadFull;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lot lot = (Lot) o;

        if (skad != null ? !skad.equals(lot.skad) : lot.skad != null) return false;
        if (dokad != null ? !dokad.equals(lot.dokad) : lot.dokad != null) return false;
        if (linia != null ? !linia.equals(lot.linia) : lot.linia != null) return false;
        if (dataWylotu != null ? !dataWylotu.equals(lot.dataWylotu) : lot.dataWylotu != null)
            return false;
        if (godzinaWylotu != null ? !godzinaWylotu.equals(lot.godzinaWylotu) : lot.godzinaWylotu != null)
            return false;
        if (dataPrzylotu != null ? !dataPrzylotu.equals(lot.dataPrzylotu) : lot.dataPrzylotu != null)
            return false;
        if (godzinaPrzylotu != null ? !godzinaPrzylotu.equals(lot.godzinaPrzylotu) : lot.godzinaPrzylotu != null)
            return false;
        if (czasLotu != null ? !czasLotu.equals(lot.czasLotu) : lot.czasLotu != null) return false;
        return cena != null ? cena.equals(lot.cena) : lot.cena == null;

    }

    @Override
    public int hashCode() {
        int result = skad != null ? skad.hashCode() : 0;
        result = 31 * result + (dokad != null ? dokad.hashCode() : 0);
        result = 31 * result + (linia != null ? linia.hashCode() : 0);
        result = 31 * result + (dataWylotu != null ? dataWylotu.hashCode() : 0);
        result = 31 * result + (godzinaWylotu != null ? godzinaWylotu.hashCode() : 0);
        result = 31 * result + (dataPrzylotu != null ? dataPrzylotu.hashCode() : 0);
        result = 31 * result + (godzinaPrzylotu != null ? godzinaPrzylotu.hashCode() : 0);
        result = 31 * result + (czasLotu != null ? czasLotu.hashCode() : 0);
        result = 31 * result + (cena != null ? cena.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Lot{" +
                "cena='" + cena + '\'' +
                ", skad='" + skad + '\'' +
                ", dokad='" + dokad + '\'' +
                ", linia='" + linia + '\'' +
                ", dataWylotu='" + dataWylotu + '\'' +
                ", godzinaWylotu='" + godzinaWylotu + '\'' +
                ", dataPrzylotu='" + dataPrzylotu + '\'' +
                ", godzinaPrzylotu='" + godzinaPrzylotu + '\'' +
                ", czasLotu='" + czasLotu + '\'' +
                ", skadFull='" + skadFull + '\'' +
                ", dokadFull='" + dokadFull + '\'' +
                '}';
    }
}
