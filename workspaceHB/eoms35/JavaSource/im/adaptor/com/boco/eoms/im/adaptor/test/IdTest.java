package com.boco.eoms.im.adaptor.test;

import java.util.List;

import com.boco.eoms.message.util.MsgHelp;

public class IdTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String id="1,Gongyufeng#1,liqiuye#2,254";
		
		List userList=MsgHelp.getUserList(id);
		
		int len=userList.size();
		
		for(int i=0;i<len;i++){
			
			System.out.println(userList.get(i));
		}

	}

}
