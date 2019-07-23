package com.work.ggr.leecode.string;

import java.util.ArrayList;
import java.util.List;

/**
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-palindromic-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author gr
 *
 */
public class 最长回文 {

	public static void main(String[] args) {
		String str="ibvjkmpyzsifuxcabqqpahjdeuzaybqsrsmbfplxycsafogotliyvhxjtkrbzqxlyfwujzhkdafhebvsdhkkdbhlhmaoxmbkqiwiusngkbdhlvxdyvnjrzvxmukvdfobzlmvnbnilnsyrgoygfdzjlymhprcpxsnxpcafctikxxybcusgjwmfklkffehbvlhvxfiddznwumxosomfbgxoruoqrhezgsgidgcfzbtdftjxeahriirqgxbhicoxavquhbkaomrroghdnfkknyigsluqebaqrtcwgmlnvmxoagisdmsokeznjsnwpxygjjptvyjjkbmkxvlivinmpnpxgmmorkasebngirckqcawgevljplkkgextudqaodwqmfljljhrujoerycoojwwgtklypicgkyaboqjfivbeqdlonxeidgxsyzugkntoevwfuxovazcyayvwbcqswzhytlmtmrtwpikgacnpkbwgfmpavzyjoxughwhvlsxsgttbcyrlkaarngeoaldsdtjncivhcfsaohmdhgbwkuemcembmlwbwquxfaiukoqvzmgoeppieztdacvwngbkcxknbytvztodbfnjhbtwpjlzuajnlzfmmujhcggpdcwdquutdiubgcvnxvgspmfumeqrofewynizvynavjzkbpkuxxvkjujectdyfwygnfsukvzflcuxxzvxzravzznpxttduajhbsyiywpqunnarabcroljwcbdydagachbobkcvudkoddldaucwruobfylfhyvjuynjrosxczgjwudpxaqwnboxgxybnngxxhibesiaxkicinikzzmonftqkcudlzfzutplbycejmkpxcygsafzkgudy";
		if(str.length()<2) {
			return ;
		}
		String result=longestPalindrome(str);
		System.out.println(result);
		  
	}
	
	static String longestPalindrome(String s) {

		if(s==null||s.equals(""))
			return "";
		else if(s.length()<=2) {
			if(istrue(s.toString())) {
				return s.toString();
			}else
				return String.valueOf(s.charAt(0));
		}
		List list=new ArrayList();
		//这里运行比较慢,优化空间很大
		int count=0;
		int maxlen=0;
		for(int i=0;i<s.length();i++) {
			for(int j=i;j<s.length();j++) {
				String temp=s.substring(i, j);
				if(istrue(temp)&&temp.length()>maxlen) {
					maxlen=temp.length();
					list.add(temp);
				}
			}
		}
		System.out.println(list);
		for(int i=0;i<list.size();i++) {
			String str=(String) list.get(i);
			if(str.length()==maxlen) {
				count=i;
			}
		}
		
		return (String) list.get(count);
			
	}
	
	//判断是否是回文
	static boolean istrue(String x) {
		StringBuffer sb = new StringBuffer(x);
		sb.reverse();
		if (sb.toString().equals(x))
			return true;
		return false;
	}
}
