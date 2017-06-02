package xstar.com.library.commons;

/**
 * author: xstar
 * since: 2015/7/28.
 */
public class NumberUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    private static String[] CN_NUMBER_LOW = new String[]
            {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static String[] CN_NUMBER_UP = new String[]
            {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static String[] CN_NUMBER = CN_NUMBER_LOW;
    private static String[] CN_NUMBER_UNIT_USE = new String[]
            {"", "十", "百", "千"};
    private static String[] CN_NUMBER_UNIT2_USE = new String[]
            {"万", "亿"};

    public static String numToCN(int num, boolean isSimple) {
        if (isSimple) CN_NUMBER = CN_NUMBER_LOW;
        else CN_NUMBER = CN_NUMBER_UP;
        StringBuilder stringBuilder = new StringBuilder();
        boolean isNegative = num < 0;
        if (isNegative) {
            num = -num;
        }
        int wan = 10000;
        int yu = 0;
        int count = 0;
        while (num > 0) {
            yu = num % wan;
            num = num / wan;
            stringBuilder.insert(0, fourLenNumToStr(yu));
            if (num > 0 && yu < 1000) {
                stringBuilder.insert(0, CN_NUMBER[0]);
            }
            if (num > 0)
                stringBuilder.insert(0, CN_NUMBER_UNIT2_USE[count]);
            count++;
        }
        if (isNegative) {
            stringBuilder.insert(0, "负");
        }
        return stringBuilder.toString();
    }

    private static String fourLenNumToStr(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        int ten = 10;
        int yu = 0;
        int count = 0;
        while (num > 0) {
            yu = num % ten;
            num = num / ten;
            stringBuilder.insert(0, CN_NUMBER[yu]);
            if (yu != 0)
                stringBuilder.insert(1, CN_NUMBER_UNIT_USE[count]);
            count++;
        }
        String numstr = stringBuilder.toString();
        numstr = numstr.replace(CN_NUMBER[0] + CN_NUMBER[0], CN_NUMBER[0]);
        if (numstr.endsWith(CN_NUMBER[0])) {
            numstr = numstr.substring(0, numstr.length() - 1);
        }
        return numstr;
    }

}
