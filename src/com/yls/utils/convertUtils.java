package com.yls.utils;
import java.util.Arrays;
import java.util.List;

public class convertUtils {
	/**
	 * ����һЩ����ת���Ĺ��߰�
	 */
	
	/**
	 * �� �ַ���  1,2,3, ת��Ϊlist
	 * @param s
	 * @return
	 */
	public static List<String> ArrayToList(String s) {
		List<String> list = null;
		String[] ss = s.substring(0,s.length()-1).split(",");
		list = Arrays.asList(ss);
		return list;
	}
}
