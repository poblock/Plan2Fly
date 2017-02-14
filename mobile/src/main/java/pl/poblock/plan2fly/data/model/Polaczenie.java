package pl.poblock.plan2fly.data.model;

import java.util.List;

import pl.poblock.plan2fly.common.MathUtils;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class Polaczenie {
    private List<Lot> loty;
    private Double suma;
    public Polaczenie(List<Lot> loty, Double suma) {
        this.loty = loty;
        this.suma = suma;
    }

    public List<Lot> getLoty() {
        return loty;
    }

    public void setLoty(List<Lot> loty) {
        this.loty = loty;
    }

    public Double getSuma() {
        return MathUtils.makeDouble(suma);
    }

    public void setSuma(Double suma) {
        this.suma = suma;
    }

    @Override
    public String toString() {
        return "Polaczenie{" +
                "loty=" + loty +
                ", suma=" + suma +
                '}';
    }
}
