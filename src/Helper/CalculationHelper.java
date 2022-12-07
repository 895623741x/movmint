package Helper;

import java.text.DecimalFormat;

public class CalculationHelper {

    // round the sales tax to the nearest 0.05
    public float salesTaxRoundUp(float price, float rate) {
        return (float) (Math.ceil(price * rate * 20.00f) / 20.00f);
    }

}
