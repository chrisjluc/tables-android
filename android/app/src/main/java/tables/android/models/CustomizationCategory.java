package tables.android.models;

public class CustomizationCategory {
    private String mName;
    private boolean mIsMultiSelect;

    public CustomizationCategory( String name, boolean isMultiSelect) {
        this.mIsMultiSelect = isMultiSelect;
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

    public boolean isMultiSelect() {
        return mIsMultiSelect;
    }

    @Override
    public int hashCode() {
        return mName.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        CustomizationCategory c = (CustomizationCategory) o;
        return this.mName.equals(c.getName());
    }
}
