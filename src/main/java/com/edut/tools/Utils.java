package com.edut.tools;

public class Utils {
	
	/**
	 * 异常不处理
	 * @param s
	 * @param errorValue
	 * @return
	 */
	public static Integer parseStr(String s , Integer errorValue) {
		try {
			Integer result = Integer.parseInt(s); 
			return result;  
		}catch (Exception e) {
			return errorValue ; 
		}
	}
}
