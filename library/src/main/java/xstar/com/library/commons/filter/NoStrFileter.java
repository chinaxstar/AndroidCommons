package xstar.com.library.commons.filter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * edittext           �������
 * Created by xstar on 2015/5/29.
 */
public class NoStrFileter implements InputFilter {
    private String noStr;
    public NoStrFileter(String noStr){
        this.noStr=noStr;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String temp = null;
        if(source.length()>0){
            temp=source.toString().replace(noStr,"");
        }
        return temp;
    }
}
