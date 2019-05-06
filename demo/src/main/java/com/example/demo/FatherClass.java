package com.example.demo;

public class FatherClass {

	public static void main(String[] args) {
		Son s=new Son();
		
		try {
			System.out.println(2<<3);
			return;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(2);
		}
	}
	
}
 class Son extends Father{
	private String a="1";

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
	
}
 class Father{
	 private String a="2";

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
	 
 }
