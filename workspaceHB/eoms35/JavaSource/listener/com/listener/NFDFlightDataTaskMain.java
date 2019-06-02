package com.listener;
import java.util.Timer;

public class NFDFlightDataTaskMain {
	 
	public static void main(String args[])
	{
		Timer timer = new Timer();
		   //设置任务计划，启动和间隔时间
		try {
			timer.schedule(new NFDFlightDataTimerTask(), 0, 600000);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}