package tables.android.models;

public class CustomizationOption {
    private String mName;
    private Money mPrice;

    public CustomizationOption(String name, Money price) {
        this.mName = name;
        this.mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public Money getPrice() {
        return mPrice;
    }

    @Override
    public boolean equals(Object o) {
        CustomizationOption co = (CustomizationOption) o;
        return mName.equals(co.getName()) && mPrice == co.getPrice();
    }

    @Override
    public int hashCode() {
        return mName.hashCode() * 5843 + mPrice.hashCode() * 5903;

    }
}
