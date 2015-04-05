package tables.android.models;

public class CustomizationOption {
    private String mName;
    private double mPrice;

    public CustomizationOption(String name, double price) {
        this.mName = name;
        this.mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public double getPrice() {
        return mPrice;
    }

    @Override
    public boolean equals(Object o) {
        CustomizationOption co = (CustomizationOption) o;
        return mName.equals(co.getName()) && mPrice == co.getPrice();
    }

    @Override
    public int hashCode() {
        return mName.hashCode() * 5843 + Double.valueOf(mPrice).hashCode() * 5903;

    }
}
