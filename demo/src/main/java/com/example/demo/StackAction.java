package com.example.demo;

/*
 * 模拟出入栈操作
 */
public class StackAction {

	public static void main(String[] args) {
		Stack st=new Stack();
		st.push(1);
		st.push(2);
		st.push(3);
		st.push(4);
	 
		
		System.out.println(st.pob());
		System.out.println(st.pob());
		System.out.println(st.pob());
		System.out.println(st.pob());
	}
}
	
	class Stack{
		int[] array=new int[20];
		int size=0;
		
		public void push(int num) {
			if(size>=array.length) {
				int[] array_new=new int[array.length*2];
				System.arraycopy(array, 0, array_new, 0, array.length);
				array=array_new;
			}else {
				array[size++]=num;
			}
		}
		
		public int pob() {
			try {
				return array[--size];
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return -1;
			}
		}
	}
	

