package xstar.com.library.commons;

import java.util.Random;

/**
 * Created by Administrator on 2015/6/29.
 */
public class RandomHelper
{
	public static Random	mRandom	= new Random();

	public static int nextColorInt()
	{
		return nextInt() | 0xff000000;
	}
	public static int nextInt(){
		return mRandom.nextInt();
	}
	public static boolean getBool(){
		return mRandom.nextBoolean();
	}

}
