package Helper;

import java.text.DecimalFormat;

public class CalculationHelper {
    public float salesTaxRoundUp(float price, float rate) {
        return (float) (Math.ceil(price * rate * 20.00f) / 20.00f);
    }
}
