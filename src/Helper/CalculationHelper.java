package Helper;

import java.text.DecimalFormat;

public class CalculationHelper {
    public float salesTaxRoundUp(float price, float rate) {
        return (float) (Math.round(price * rate * 20.0) / 20.0);
    }
}
