package com.tools.edge.dynamic.island.ui.component.background;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationManager;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.palette.graphics.Palette;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.ui.component.utils.Constants;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import jp.wasabeef.blurry.Blurry;

public class Utils {
    public static final int PERMISSIONS_REQUEST_READ_EXT = 300;
    public static final int PERMISSIONS_REQUEST_SET_WALLPAPER = 200;
    public static final int PERMISSIONS_REQUEST_WRITE_EXT = 100;
    
    public String TAG = "Utils";
    
    public Context _context;
    private PrefManager pref;

    public Utils(Context context) {
        this._context = context;
        this.pref = new PrefManager(this._context);
    }

    public int getScreenWidth() {
        Display defaultDisplay = ((WindowManager) this._context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        try {
            defaultDisplay.getSize(point);
        } catch (NoSuchMethodError unused) {
            point.x = defaultDisplay.getWidth();
            point.y = defaultDisplay.getHeight();
        }
        return point.x;
    }

    public void saveImageToSDCard(Bitmap bitmap) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), this.pref.getGalleryName());
        file.mkdirs();
        final File file2 = new File(file, "Wallpaper-" + new Random().nextInt(10000) + ".jpg");
        if (file2.exists()) {
            file2.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            ((Activity) this._context).runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(Utils.this._context, "Wallpaper Set", Toast.LENGTH_SHORT).show();
                    Log.d(Utils.this.TAG, "Wallpaper saved to: " + file2.getAbsolutePath());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            ((Activity) this._context).runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(Utils.this._context, Utils.this._context.getString(R.string.toast_saved_failed), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static String saveToInternalSorage(final Context context, Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                Constants.setWallpaperColor(context, palette.getVibrantColor(context.getResources().getColor(R.color.selected)));
            }
        });
        File file = new File(context.getDir("imageDir", 0), context.getString(R.string.backgroundImageName));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ImageView imageView = new ImageView(context);
            Blurry.with(context).radius(25).sampling(4).color(Color.argb(66, 150, 150, 150)).from(bitmap).into(imageView);
            ((BitmapDrawable) imageView.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
            Constants.setWallpaperUpdated(context, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    public void setAsWallpaper(Bitmap bitmap) {
        saveToInternalSorage(this._context, bitmap);
        try {
            WallpaperManager.getInstance(this._context).setBitmap(bitmap);
            ((Activity) this._context).runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(Utils.this._context, Utils.this._context.getString(R.string.toast_wallpaper_set), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            ((Activity) this._context).runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(Utils.this._context, Utils.this._context.getString(R.string.toast_wallpaper_set_failed), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static String saveImage(Bitmap bitmap) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/complockwallpaper.jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getPath();
    }

    public static boolean isLocationEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }

    public static boolean isAdsRemoved(Context context) {
        return context.getSharedPreferences("ads", 0).getBoolean("isAdRemoved", false);
    }
}
