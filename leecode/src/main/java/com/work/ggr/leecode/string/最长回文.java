package com.work.ggr.leecode.string;

public class 最长回文 {

	public static void main(String[] args) {
		String str="babad";
		if(str.length()<2) {
			return ;
		}
		StringBuffer sb=new StringBuffer(str);
		  
	}
	
	static boolean istrue(String x) {
		String str = String.valueOf(x);

		StringBuffer sb = new StringBuffer();
		for (int i = str.length() - 1; i >= 0; i--) {
			sb.append(str.charAt(i));
		}
		if (sb.toString().equals(str))
			return true;
		return false;
	}
}
