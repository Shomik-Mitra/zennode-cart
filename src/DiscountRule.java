// Class to encapsulate DiscountRule details
public class DiscountRule {
    private DiscountType type;
    private double threshold;
    private String discountPercantage;

    public DiscountRule(DiscountType type, double threshold, String discountPercantage) {
        this.type = type;
        this.threshold = threshold;
        this.discountPercantage = discountPercantage;
    }

    public DiscountType getType() {
        return type;
    }

    public double getThreshold() {
        return threshold;
    }

    public String getDiscountPercantage() {
        return discountPercantage;
    }
}
