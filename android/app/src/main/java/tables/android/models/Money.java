package tables.android.models;


import java.text.NumberFormat;
import java.util.Locale;

public class Money {
    private double mAmount;
    private NumberFormat mCurrencyFormatter;

    public Money(double amount) {
        this.mAmount = amount;
        Locale locale = new Locale("en", "US");
        mCurrencyFormatter = NumberFormat.getCurrencyInstance(locale);
    }

    public Money add(Money m) {
        return new Money(this.mAmount + m.getAmount());
    }

    public double getAmount() {
        return mAmount;
    }

    @Override
    public String toString() {
        return mCurrencyFormatter.format(mAmount);
    }

    @Override
    public int hashCode() {
        return Double.valueOf(mAmount).hashCode();
    }
}
