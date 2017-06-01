package xstar.com.library.commons;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

import xstar.com.library.commons.javacommons.Assert;
import xstar.com.library.commons.javacommons.Empty;


/**
 * @author: xstar
 * @since: 2017-05-23.
 */

public class PermissionUtil {
    public static void verifyPermission(Activity activity, int requestCode, String... permissions) {
        Assert.notNullCheck(permissions, "permissions ==null");
        List<String> list = new ArrayList<>();
        for (String p : permissions) {
            if (!isGranted(activity, p)) {
                list.add(p);
            }
        }
        if (Empty.notEmpty(list))
            ActivityCompat.requestPermissions(activity, list.toArray(new String[list.size()]), requestCode);
    }

    public static boolean isGranted(Context context, String permission) {
        return !isMarshmallow() || isGranted_(context, permission);
    }

    private static boolean isGranted_(Context context, String permission) {
        int checkP = ActivityCompat.checkSelfPermission(context, permission);
        return checkP == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

}
