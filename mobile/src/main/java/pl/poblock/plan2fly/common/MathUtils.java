package pl.poblock.plan2fly.common;

import java.math.BigDecimal;

/**
 * Created by krzysztof.poblocki on 2017-02-13.
 */

public class MathUtils {
    public static double makeDouble(double value) {
        BigDecimal bd = new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
}
