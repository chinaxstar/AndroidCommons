package xstar.com.library.commons;

import java.util.Random;

/**
 * Created by Administrator on 2015/6/29.
 */
public class RandUtils {
    public static Random mRandom = new Random();

    public static int nextColorInt() {
        return nextInt() | 0xff000000;
    }

    public static int nextInt() {
        return mRandom.nextInt();
    }

    public static boolean nextBool() {
        return mRandom.nextBoolean();
    }

    /**
     * [0,num)
     *
     * @param num
     * @return
     */
    public static int nextInt(int num) {
        return mRandom.nextInt(num);
    }

    /**
     * [0,1.0)
     *
     * @return
     */
    public static double nextD() {
        return mRandom.nextDouble();
    }

    /**
     * 高斯函数
     *
     * @return
     */
    public static double nextGass() {
        return mRandom.nextGaussian();
    }
}
