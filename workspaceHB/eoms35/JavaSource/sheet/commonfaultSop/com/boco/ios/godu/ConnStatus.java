package com.boco.ios.godu;
/**
 * 命令执行后的状态
 * (公共类: 支持GODU、NeTelnetD)
 * @author 闫保亮
 *
 */
public class ConnStatus {
	/** 未连接统一指令平台（或NeTelnetD） */
	public static final int NOT_CONNECTED_GODU = -3;
	/** 连接统一指令平台（或NeTelnetD）成功 */
	public static final int CONNECTED_GODU = -2;
	/** 连接网元成功 */
	public static final int CONNECTED_NE = -1;

	/** 命令成功结束，收到期望值 */
	public static final int CMD_SUCCESS = 0;
	/** 未收到期望值，时间超时 */
	public static final int CMD_TIMEOUT = 1;



	/** 统一指令平台（或NeTelnetD）状态切换异常 */
	public static final int CMD_STATUS_SWITCH_ERROR = 5;

}
