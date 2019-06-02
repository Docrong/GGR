package com.boco.eoms.sequence;

import java.util.Observable;
import java.util.Observer;

/**
 * <p>
 * Title:序列观察者
 * </p>
 * <p>
 * Description:当序列有变动，通知update
 * </p>
 * <p>
 * Date:Apr 25, 2008 3:18:21 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class SequenceObserver implements Observer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		Sequence sequence = (Sequence) o;
		// 若sequence无线程在跑;否则若sequence有线程在跑,不启动新线程
		 if (!sequence.isThreadRunning()) {
		// 设置线程在跑
		sequence.setThreadRunning(true);
		// 启动线程执行序列
		// new Thread(new DoJobThread(sequence)).start();
		Thread thread = new Thread(new DoJobThread(sequence));
		thread.start();

		// TODO 加这句让方法等待线程结束是正确执行
		// while (sequence.isThreadRunning()) {
		//		
		 }

	}
}
