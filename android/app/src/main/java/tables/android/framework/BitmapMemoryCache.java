package tables.android.framework;

import android.graphics.Bitmap;
import android.util.LruCache;

public class BitmapMemoryCache {
    private static BitmapMemoryCache mBitmapMemoryCache;
    private LruCache<String, Bitmap> mMemoryCache;

    public static BitmapMemoryCache getInstance() {
        if (mBitmapMemoryCache == null)
            mBitmapMemoryCache = new BitmapMemoryCache();
        return mBitmapMemoryCache;
    }

    private BitmapMemoryCache() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }
}
