package xstar.com.commons.utils;

import java.util.Random;

/**
 * Created by Administrator on 2015/6/29.
 */
public class RandomUtil {
    public static Random mRandom	= new Random();

    public static int getRandomColorInt()
    {
        return Math.abs(mRandom.nextInt()) | 0xff000000;
    }

    public static Random getRandom(){
        return mRandom;
    }
}
