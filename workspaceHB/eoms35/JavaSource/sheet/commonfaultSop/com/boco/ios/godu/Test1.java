package com.boco.ios.godu;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

public class Test1 extends Thread {
	private Logger logger = Logger.getLogger(this.getClass());
	AtomicInteger okint;
	AtomicInteger errorint;
	AtomicInteger cmdCount;
	List report;
	long neid;

	public Test1(long neid, AtomicInteger okint, AtomicInteger errorint,
			List report, AtomicInteger cmdCount) {
		this.neid = neid;
		this.okint = okint;
		this.errorint = errorint;
		this.report = report;
		this.cmdCount = cmdCount;
	}

	public void run() {
		logger.info("开始初始化");
		ConnectionImplMyGodu conn = new ConnectionImplMyGodu();
		conn.setHost("10.25.0.219");
		conn.setPort(1235);
		conn.setUsernamePrompt("login:");
		conn.setUsername("admin");
//		conn.setUsername("zmw");
		conn.setPasswordPrompt("password:");
		conn.setPassword("gaojing!@#321");
//		conn.setPassword("znw123");
		conn.setLoginOkPrompt("[GODU-CMD]");
		conn.setWaitPromptTime(10000L);

		logger.info("初始化完成");
		try {
			conn.connect("admin");
			// conn.connect();
			String prompt = null;
			if (conn.open(neid)) {
				logger.info("连接网元成功");
			} else {
				logger.info("连接网元失败");
				throw new Exception("连接失败");
			}
			List cmdList = new ArrayList();
			String[] cmdarr = null;

			// cmdarr = new String[]{"sar 1 10调延时180","20000",""};
			// cmdList.add(cmdarr);
			cmdarr = new String[] { "Allip;", "6000", "" };
			cmdList.add(cmdarr);
			cmdarr = new String[] { "quit;", "10000", "" };
			cmdList.add(cmdarr);
			cmdarr = new String[] { "exit;", "10000", "" };
			cmdList.add(cmdarr);

			// cmdarr = new String[]{getPartingStr(4)+"回车","10000",""};
			// cmdList.add(cmdarr);
			// cmdarr = new String[]{"123","10000",""};
			// cmdList.add(cmdarr);

			// cmdarr = new String[]{"ls -alt","6000","end===&"};
			// cmdList.add(cmdarr);
			//
			// cmdarr = new String[]{"vmstat 1 5","2000",""};
			// cmdList.add(cmdarr);
			//
			// cmdarr = new String[]{"perl -v","1000",""};
			// cmdList.add(cmdarr);
			//
			// cmdarr = new String[]{"uname -a","2000",""};
			// cmdList.add(cmdarr);
			//
			// cmdarr = new String[]{"ifconfig -a","10000",""};
			// cmdList.add(cmdarr);
			//
			// cmdarr = new String[]{"who","10000",""};
			// cmdList.add(cmdarr);
			//
			// cmdarr = new String[]{"pwd","2000",""};
			// cmdList.add(cmdarr);
			//
			// cmdarr = new String[]{"ls -ltr","4000",""};
			// cmdList.add(cmdarr);

			cmdCount.set(cmdList.size());
			StringBuilder strb = new StringBuilder();
			for (int i = 0; i < cmdList.size(); i++) {
				
				strb.delete(0, strb.length());
				String[] sCmdStr = (String[])cmdList.get(i);
				if (sCmdStr[0] == null) {
					continue;
				}
				String re = conn.sendCmd(sCmdStr[0],
						Long.parseLong(sCmdStr[1]), sCmdStr[2]);

				strb.append("\r\n====================================1\r\n");
				strb.append("网元:" + neid + "\r\n");
				strb.append("发送:" + sCmdStr[0] + "\r\n");
				strb.append("期望:" + sCmdStr[2] + "\r\n");
				strb.append("等待:" + sCmdStr[1] + "\r\n");

				switch (conn.getStatus()) {
				case -3:
					strb.append("状态:未连接统一指令平台\r\n");
					break;
				case -2:
					strb.append("状态:连接统一指令平台成功\r\n");
					break;
				case -1:
					strb.append("状态:连接网元成功\r\n");
					break;
				case 0:
					strb.append("状态:命令成功结束，收到期望值\r\n");
					break;
				case 1:
					strb.append("状态:未收到期望值，时间超时\r\n");
					break;
				case 5:
					strb.append("状态:统一指令平台状态切换异常\r\n");
					break;
				default:
					strb.append("状态:其它\r\n");
				}

				if (re == null) {
					strb.append("结果:null\r\n");
				} else if (re.length() == 0) {
					strb.append("结果:空\r\n");
				} else {
					strb.append("结果:" + re + "\r\n");
					report.add("report");
				}

				strb.append("====================================2\r\n");
				logger.info(strb.toString());

			}

			logger.info("正常结束!!");
			okint.incrementAndGet();
		} catch (Exception e) {
			logger.info("检查异常!!");
			errorint.incrementAndGet();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				logger.error("关闭连接时异常");
			}
		}
	}

	public static void main(String[] args) {
		List list = new ArrayList();
		List report = new Vector();
		List nelist = new ArrayList();
		int count = 1;
		AtomicInteger okint = new AtomicInteger(0);
		AtomicInteger errorint = new AtomicInteger(0);
		AtomicInteger cmdCount = new AtomicInteger(0);
		nelist.add(new Long(-1453970567L));
		// nelist.add(-531467074L);
		// nelist.add(1530556268L);

		for (int i = 0; i < nelist.size(); i++) {
			Long neid = (Long) nelist.get(i);
			for (int j= 0; j < count; j++) {
				list.add(new Test1(neid.longValue(), okint, errorint, report, cmdCount));
			}
		}

		for (int i = 0; i < list.size(); i++) {
			Test1 t = (Test1) list.get(i);
			t.start();
		}

		while (true) {
			if (okint.get() + errorint.get() == list.size()) {
				break;
			}
			try {
				Thread.sleep(5000);
				System.out.println("临时正常:" + okint.get());
				System.out.println("临时异常:" + errorint.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("期望报告：" + (list.size() * cmdCount.get()));
		System.out.println("得到报告：" + report.size());
		System.out.println("网元正常结束：" + okint);
		System.out.println("网元异常结束：" + errorint);

	}

	public String getPartingStr(int parting) {
		byte[] b = new byte[1];
		b[0] = (byte) parting;
		String str = new String(b);
		return str;
	}
}