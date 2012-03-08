package cn.bstar.gale.boss.util;
   
public class DataFormatUtils {
	

	
	/**
	 * 判断字符串是否为空
	 * @param s
	 * @return
	 */
	public static boolean isStringEmpty(String s) {
		return (s == null || "".equals(s.trim()));
	}

}
