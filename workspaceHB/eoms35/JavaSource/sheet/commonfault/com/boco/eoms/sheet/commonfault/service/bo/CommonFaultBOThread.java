package com.boco.eoms.sheet.commonfault.service.bo;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.boco.eoms.commonfaultIrontower.ForwardServerToTower;
import com.boco.eoms.commonfaultIrontower.ForwardServerToTowerServiceLocator;

public class CommonFaultBOThread extends Thread{
	String opDetail;
	String mainTownerDeviceId;//铁塔站址资源编码 不能为空
	public CommonFaultBOThread(String opDetail,String mainTownerDeviceId){
		this.opDetail = opDetail;
		this.mainTownerDeviceId = mainTownerDeviceId;
	}
	
	public void run(){
		System.out.println("input thread by lyg");
		System.out.println("opDetail==lyg=="+opDetail);
		System.out.println("mainTownerDeviceId==lyg=="+mainTownerDeviceId);
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("InterruptedException,thread");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("sleep time==lyg=="+(endTime-startTime)/1000);
		 //调用接口
        ForwardServerToTowerServiceLocator  service = new ForwardServerToTowerServiceLocator();
        ForwardServerToTower bing = null;
		try {
			bing = service.getTaskToTower();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println("err==townerSend====bing==="+bing);
			e.printStackTrace();
		}
        String returnOpt="";
        System.out.println("mainTownerDeviceId==="+mainTownerDeviceId);
		try {
			if(bing != null){
				if(!"".equals(mainTownerDeviceId)){
					returnOpt = bing.newWorksheet("", "", "", "", opDetail);
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("err==townerSend====returnOpt===");
			e.printStackTrace();
		}
		
	}
}
