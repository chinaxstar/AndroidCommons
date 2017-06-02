package xstar.com.library.commons.handler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * @author xstar on 2015/9/2.
 */
public abstract class HandlerAbs<T>  extends Handler{
    WeakReference<T> weakT;
    public HandlerAbs(T mthis){
        weakT=new WeakReference<>(mthis);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        T mthis=weakT.get();
        if(mthis!=null){
            handleMessageSafely(msg,mthis);
        }else {
            Log.e("handleMessage","templete is  null!");
        }
    }

    public abstract void handleMessageSafely(Message msg,T mthis);
}
