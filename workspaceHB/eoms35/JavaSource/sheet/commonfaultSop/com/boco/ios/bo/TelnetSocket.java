package com.boco.ios.bo;



import com.boco.ios.godu.ConnectionImplMyGodu;

public class TelnetSocket {
	private ConnectionImplMyGodu conn = new ConnectionImplMyGodu();

	public void init(Long neid) {
		conn.setHost("10.25.0.219");
		conn.setPort(1235);
		conn.setUsernamePrompt("login:");
		conn.setUsername("admin");
		conn.setPasswordPrompt("password:");
		conn.setPassword("gaojing!@#321");
		conn.setLoginOkPrompt("[GODU-CMD]");
		conn.setWaitPromptTime(10000L);
		try {
			conn.connect("admin");
			if (conn.open(neid.longValue())) {
				System.out.println("连接网元成功");
			} else {
				System.out.println("连接网元失败");
				throw new Exception("连接失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String send(String cmd, Long time, String str) {
		try {
			String re = conn.sendCmd(cmd, time.longValue(), "");
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public int getStatus() {
		return conn.getStatus();
	}

	public void close() {
		try {
			conn.close();
			System.out.println("关闭连接");
		} catch (Exception e) {
			System.out.println("关闭连接时异常");
		}
	}

	public static void main(String[] args) {
		TelnetSocket obj = new TelnetSocket();
		StringBuffer strb = new StringBuffer();
		Long neid = new Long(-1453970567L);
		obj.init(neid);
		String cmd = "Allip;";
		Long time = new Long(1000L);
		String re = obj.send(cmd, time, "");
		switch (obj.getStatus()) {
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

		System.out.println("================================\r\n" + strb);
		System.out.println("==============1==================\r\n" + re.trim());
		
		
		
		obj.close();
	}
}
