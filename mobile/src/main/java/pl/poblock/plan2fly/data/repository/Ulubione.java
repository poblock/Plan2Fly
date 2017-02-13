package pl.poblock.plan2fly.data.repository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import pl.poblock.plan2fly.common.MathUtils;
import pl.poblock.plan2fly.data.model.Lot;
import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.model.Polaczenie;

/**
 * Created by krzysztof.poblocki on 2017-02-13.
 */

public class Ulubione {

    private static List<Lot> etapyTam = new LinkedList<>();
    private static List<Lot> etapyPowrot = new LinkedList<>();

    public static Podroz preparePodroz() {
        if(!etapyTam.isEmpty() && !etapyPowrot.isEmpty()) {
            Polaczenie tam = preparePolaczenie(0);
            Polaczenie powrot = preparePolaczenie(1);
            double razem = MathUtils.makeDouble(tam.getSuma() + powrot.getSuma());
            return new Podroz(tam, powrot, razem);
        }
        return null;
    }

    public static Polaczenie preparePolaczenie(int tryb) {
        if(tryb==0) {
            double cena = 0.0;
            for(Lot l : etapyTam) {
                cena += Double.parseDouble(l.getCena());
            }
            return new Polaczenie(etapyTam, MathUtils.makeDouble(cena));
        } else {
            double cena = 0.0;
            for(Lot l : etapyPowrot) {
                cena += Double.parseDouble(l.getCena());
            }
            return new Polaczenie(etapyPowrot, MathUtils.makeDouble(cena));
        }
    }

    public static void update(boolean dodaj, int tryb, List<Lot> loty) {
        if(dodaj) {
            if(tryb==0) {
                etapyTam.addAll(loty);
            } else {
                etapyPowrot.addAll(loty);
            }
        } else {
            if(tryb==0) {
                etapyTam.clear();
            } else {
                etapyPowrot.clear();
            }
        }
    }
}
