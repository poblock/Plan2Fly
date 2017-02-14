package pl.poblock.plan2fly.data.model;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class Podroz {
    private Polaczenie podrozTam;
    private Polaczenie podrozPowrot;
    private Double cenaRazem;
    private boolean najtanszyWylot;
    private boolean najtanszyPowrot;

    public Podroz(Polaczenie podrozTam, Polaczenie podrozPowrot, Double cenaRazem) {

        this.podrozTam = podrozTam;
        this.podrozPowrot = podrozPowrot;
        this.cenaRazem = cenaRazem;
    }

    public Polaczenie getPodrozTam() {
        return podrozTam;
    }

    public void setPodrozTam(Polaczenie podrozTam) {
        this.podrozTam = podrozTam;
    }

    public Polaczenie getPodrozPowrot() {
        return podrozPowrot;
    }

    public void setPodrozPowrot(Polaczenie podrozPowrot) {
        this.podrozPowrot = podrozPowrot;
    }

    public Double getCenaRazem() {
        return cenaRazem;
    }

    public void setCenaRazem(Double cenaRazem) {
        this.cenaRazem = cenaRazem;
    }

    public boolean isNajtanszyPowrot() {
        return najtanszyPowrot;
    }

    public void setNajtanszyPowrot(boolean najtanszyPowrot) {
        this.najtanszyPowrot = najtanszyPowrot;
    }

    public boolean isNajtanszyWylot() {
        return najtanszyWylot;
    }

    public void setNajtanszyWylot(boolean najtanszyWylot) {
        this.najtanszyWylot = najtanszyWylot;
    }


    @Override
    public String toString() {
        return "Podroz{" +
                "cenaRazem=" + cenaRazem +
                ", podrozTam=" + podrozTam +
                ", podrozPowrot=" + podrozPowrot +
                ", najtanszyWylot=" + najtanszyWylot +
                ", najtanszyPowrot=" + najtanszyPowrot +
                '}';
    }
}
