package tables.android.ui.components;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class TablesEditText extends EditText {

    private static Typeface typeface;

    public TablesEditText(Context context) {
        super(context);
        setTypeFace(context);
    }

    public TablesEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeFace(context);
    }

    public TablesEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTypeFace(context);
    }

    private void setTypeFace(Context context) {
        if (typeface == null)
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Bariol_Regular.otf");
        this.setTypeface(typeface);
    }
}