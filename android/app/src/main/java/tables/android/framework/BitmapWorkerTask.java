package tables.android.framework;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.net.URL;

import tables.android.utils.BitmapUtils;

public class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = "BitmapWorkerTask";
    private final WeakReference<ImageView> imageViewReference;
    private final Integer reqHeight;
    private final Integer reqWidth;
    private String mUrlString;

    public BitmapWorkerTask(ImageView imageView) {
        this.imageViewReference = new WeakReference<ImageView>(imageView);
        this.reqHeight = null;
        this.reqWidth = null;
    }

    public BitmapWorkerTask(ImageView imageView, Integer reqHeight, Integer reqWidth) {
        this.imageViewReference = new WeakReference<ImageView>(imageView);
        this.reqHeight = reqHeight;
        this.reqWidth = reqWidth;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            mUrlString = params[0];
            URL url = new URL(mUrlString);
            Bitmap bitmap;
            if (reqHeight == null || reqWidth == null)
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            else {
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                options.inSampleSize = BitmapUtils.calculateInSampleSize(options, reqWidth, reqHeight);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options);
            }
            BitmapMemoryCache.getInstance().addBitmapToMemoryCache(mUrlString, bitmap);
            return bitmap;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            final BitmapWorkerTask bitmapWorkerTask =
                    BitmapManager.getBitmapWorkerTask(imageView);
            if (this == bitmapWorkerTask && imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public String getUrlString() {
        return mUrlString;
    }
}