package xstar.com.library.commons.javacommons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JavaMd5
{

	public static String toMd5B16(String plainText)
	{
		String result = toMd5(plainText);
		if (result != null) result = result.substring(8, 24);
		return result;
	}

	public static String toMd5(String plainText)
	{
		String result = null;
		try
		{
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++)
			{
				i = b[offset];
				if (i < 0) i += 256;
				if (i < 16) buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString().toUpperCase(); // md5 32bit
			System.out.println("mdt 16bit: " + buf.toString().substring(8, 24));
			System.out.println("md5 32bit: " + buf.toString());
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
