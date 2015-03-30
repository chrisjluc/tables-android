package tables.android.ui.components;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TablesBoldTextView extends TextView {

    private static Typeface typeface;

    public TablesBoldTextView(Context context) {
        super(context);
        setTypeFace(context);
    }

    public TablesBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeFace(context);
    }

    public TablesBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTypeFace(context);
    }

    private void setTypeFace(Context context) {
        if (typeface == null)
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Bariol_Bold.otf");
        this.setTypeface(typeface);
    }
}