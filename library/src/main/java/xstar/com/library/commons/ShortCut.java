package xstar.com.library.commons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * @since 2015/8/19.
 */
public class ShortCut {
    public static void createShortCut(Context context,String shortCut,Class<Activity> startClass,int shotcutresis){
        // 创建快捷方式的Intent
        Intent shortcutintent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // 不允许重复创建
        shortcutintent.putExtra("duplicate", false);
        // 需要现实的名称
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCut);
        // 点击快捷图片，运行的程序主入口
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClass(context,startClass);
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        // 快捷图片
        Intent.ShortcutIconResource icon = Intent.ShortcutIconResource.fromContext(context, shotcutresis);
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        // 发送广播。OK
        context.sendBroadcast(shortcutintent);
    }
}
