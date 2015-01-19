package tables.android.framework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import tables.android.R;
import tables.android.ui.components.AsyncDrawable;

public class BitmapManager {
    private static BitmapManager mBitMapManager;
    private Bitmap mPlaceHolderBitmap;
    private BitmapMemoryCache mBitmapMemoryCache;
    private Context mContext;

    public static void init(Context context) {
        if (mBitMapManager == null) {
            mBitMapManager = new BitmapManager(context);
        }
    }

    public static BitmapManager getInstance() {
        return mBitMapManager;
    }

    private BitmapManager(Context context) {
        mContext = context;
        mPlaceHolderBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tables_logo);
        mBitmapMemoryCache = BitmapMemoryCache.getInstance();
    }

    public void loadBitmap(String url, ImageView imageView) {
        Bitmap bitmap = mBitmapMemoryCache.getBitmapFromMemoryCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else if (cancelPotentialWork(url, imageView)) {
            final BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            final AsyncDrawable asyncDrawable =
                    new AsyncDrawable(mContext.getResources(), mPlaceHolderBitmap, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(url);
        }
    }

    public static boolean cancelPotentialWork(String data, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final String bitmapData = bitmapWorkerTask.getUrlString();
            // If bitmapData is not yet set or it differs from the new data
            if (bitmapData == null || bitmapData != data) {
                // Cancel previous task
                bitmapWorkerTask.cancel(true);
            } else {
                // The same work is already in progress
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }

    public static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }
}
