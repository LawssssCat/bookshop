package com.edut.tools;

public class Utils {
	
	/**
	 * 异常不处理
	 * @param s
	 * @param errorValue
	 * @return
	 */
	public static Integer parseStr(String s , Integer n ) throws NumberFormatException {
		try {
			Integer result = Integer.parseInt(s); 
			return result;  
		}catch (NumberFormatException e) {
			return n ; 
		}
	}
}
