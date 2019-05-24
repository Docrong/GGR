package com.boco.eoms.duty.bo;

public class TawRmPlanContentBO {
	private TawRmPlanContentBO(){
		
	}
	private static TawRmPlanContentBO instance=null;
	
	public static TawRmPlanContentBO getInstance(){
		if(instance==null){
			instance=init();
		}
		return instance;
	}
	private static TawRmPlanContentBO init(){
		instance=new TawRmPlanContentBO();
		return instance;
	}
}
