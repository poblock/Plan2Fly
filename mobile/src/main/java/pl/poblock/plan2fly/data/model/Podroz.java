package pl.poblock.plan2fly.data.model;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class Podroz {
    private Polaczenie podrozTam;
    private Polaczenie podrozPowrot;
    private Double cenaRazem;

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

    @Override
    public String toString() {
        return "Podroz{" +
                "podrozTam='" + podrozTam + '\'' +
                ", podrozPowrot='" + podrozPowrot + '\'' +
                ", cenaRazem=" + cenaRazem +
                '}';
    }
}
