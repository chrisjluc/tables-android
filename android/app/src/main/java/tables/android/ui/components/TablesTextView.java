package tables.android.ui.components;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TablesTextView extends TextView {

    private static Typeface typeface;

    public TablesTextView(Context context) {
        super(context);
        setTypeFace(context);
    }

    public TablesTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeFace(context);
    }

    public TablesTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTypeFace(context);
    }

    private void setTypeFace(Context context) {
        if (typeface == null)
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Bariol_Regular.otf");
        this.setTypeface(typeface);
    }
}
