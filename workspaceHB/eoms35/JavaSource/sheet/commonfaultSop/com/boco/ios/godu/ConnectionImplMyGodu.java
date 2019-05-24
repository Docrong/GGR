package com.boco.ios.godu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.loging.BocoLog;


/**
 * 网元操作器
 * @author 闫保亮
 */
public class ConnectionImplMyGodu extends TelnetClientMyGodu implements Connection {


	/** 指令平台主机 */
	private String host;
	/** 指令平台端口 */
	private int port;
	/** 输入用户名提示符 */
	private String usernamePrompt;
	/** 用户名 */
	private String username;
	/** 输入密码提示符 */
	private String passwordPrompt;
	/** 密码 */
	private String password;
	/** 登录成功提示符 */
	private String loginOkPrompt;
	/** 命令执行完毕后等待提示符最长时间（毫秒） */
	private long waitPromptTime;
	/** 网元ID */
	private long neId;
	/** 连接状态 */
	private int status;
	private AtomicBoolean timeOutFlag;

	/** 原始报告缓冲容量 */
	private long reportCacheCapacity = 102400;


	/**
	 * 构造方法
	 */
	public ConnectionImplMyGodu() {
		this.status = ConnStatus.NOT_CONNECTED_GODU;
		this.timeOutFlag=new AtomicBoolean(false);
	}

	public void connect() throws Exception {
		connect(username);
	}
	
	public void connect(String channelName) throws Exception {
		setReportCacheCapacityClient(reportCacheCapacity);
		BocoLog.info(ConnectionImplMyGodu.class,"开始连接统一指统平台");
		connect(host, port);
		readUntil(usernamePrompt,waitPromptTime,timeOutFlag);
		if(timeOutFlag.get()){throw new Exception("连接统一指令平台出错:未收到" + usernamePrompt);}
		write(channelName);
		readUntil(passwordPrompt,waitPromptTime,timeOutFlag);
		if(timeOutFlag.get()){throw new Exception("连接统一指令平台出错:未收到" + passwordPrompt);}
		write(password);
		readUntil(loginOkPrompt,waitPromptTime,timeOutFlag);
		if(timeOutFlag.get()){throw new Exception("连接统一指令平台出错:密码错误");}
		status = ConnStatus.CONNECTED_GODU;
		BocoLog.info(ConnectionImplMyGodu.class,"连接统一指统平台成功");

	}

	
	public boolean open(final long neId) throws Exception {
		BocoLog.info(ConnectionImplMyGodu.class,"开始连接网元" + neId);
		if (status != ConnStatus.CONNECTED_GODU) {
			BocoLog.info(ConnectionImplMyGodu.class,"连接网元失败:" + status);
			return false;
		}
		this.neId = neId;
		boolean conStatus = false;

		String promptStr  = "end===" + Prompt.GODU_SEND_CMD_END + " end===" + Prompt.GODU_CONN_NE_END;



		String result = sendCmd("cn id=" + neId + " returnxml=y", waitPromptTime, promptStr, true,true);
		if(!timeOutFlag.get() && ( result.indexOf("连接成功并设置命令结束成功") > -1 || result.indexOf("连接成功并执行登录脚本") > -1|| result.indexOf("<") > -1)){
			conStatus = true;
			status = ConnStatus.CONNECTED_NE;
			BocoLog.info(ConnectionImplMyGodu.class,"连接网元成功:" + neId);
		}else{
			BocoLog.info(ConnectionImplMyGodu.class,"连接网元失败:" + neId);
			conStatus = false;
		}
		return conStatus;
	}



	
	public String sendCmd(String cmd,long timeOut,String promptStr) throws Exception{
		if(timeOut <= 0){
			timeOut = waitPromptTime;
		}
		if(promptStr.length() > 0){
			return sendCmd(cmd,timeOut,promptStr,false,false);
		}else{
			return sendCmd(cmd,timeOut,Prompt.GODU_SEND_CMD_END,true,false);
		}
	}

	/**
	 * 发送命令
	 * @param cmd 命令
	 * @param timeOut 超时时间
	 * @param prompt 期望值
	 * @param isDefaultPrompt 期望值类型
	 * @param isOpenNe 是否为连接网元
	 * @return 命令执行结果
	 * @throws Exception
	 */
	private String sendCmd(String cmd,long timeOut,String promptStr,boolean isDefaultPrompt,boolean isOpenNe) throws Exception {
		if(status < ConnStatus.CONNECTED_GODU){
			return null;
		}
		String reStr = null;
		String sendCmdAdd = "";
		StringBuffer loginfo = new StringBuffer();
		loginfo.append("[" + neId + "]发送命令:" + cmd + "\t超时时间:" + timeOut + "ms");
		if(!isOpenNe){
			if(status < ConnStatus.CONNECTED_NE){
				return null;
			}
		}
		if(isDefaultPrompt){
			loginfo.append(" 期望值：默认");
		}else {
			loginfo.append(" 期望值：").append(promptStr);
		}
		BocoLog.info(ConnectionImplMyGodu.class,loginfo.toString());
		BocoLog.info(ConnectionImplMyGodu.class,"[开始]发送命令:" + cmd);
		String result = send(cmd, getPromptArray(promptStr),timeOut,timeOutFlag);
		BocoLog.info(ConnectionImplMyGodu.class,"[结束]发送命令:" + cmd);
		if(timeOutFlag.get()){
			status = ConnStatus.CMD_TIMEOUT;
		}else{
			status = ConnStatus.CMD_SUCCESS;
		}
		loginfo.append("\t回显:").append(result);
		BocoLog.info(ConnectionImplMyGodu.class,loginfo.toString());
		return result;
	}

	
	public void close() throws Exception {
		BocoLog.info(ConnectionImplMyGodu.class,neId + "网元关闭统一指统平台");
		disconnect();
		status = ConnStatus.NOT_CONNECTED_GODU;
	}
	/**
	 * 得到期望值数组
	 * @param promptString
	 * @return
	 */
	private String[] getPromptArray(String promptString){
		String[] promptArray = null;
		List promptList = new ArrayList();
		promptArray = promptString.split("end===");
		for(int i=0;i<promptArray.length;i++){
			if(promptArray[i].trim().length() == 0){continue;}
			promptList.add(promptArray[i]);
		}
		promptArray = new String[promptList.size() + 5];
		promptArray[0] = Prompt.GODU_SEND_CMD_TIMEOUT;
		promptArray[1] = Prompt.GODU_SEND_CMD_SUCCESS;
		promptArray[2] = Prompt.GODU_SEND_CMD_WRONG;
                promptArray[3] = Prompt.GODU_CONN_GODU_START;
                promptArray[4] = Prompt.GODU_CONN_NE_END;
		for(int i=0;i<promptList.size();i++){
			promptArray[i+5] = ((String)promptList.get(i)).trim();
		}
		if(promptArray.length == 0){
			promptArray = new String[]{Prompt.GODU_SEND_CMD_END};
		}
		return promptArray;
	}

	public int getStatus(){
		return status;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getWaitPromptTime() {
		return waitPromptTime;
	}

	public void setWaitPromptTime(long waitPromptTime) {
		if(waitPromptTime <= 0){
			this.waitPromptTime = 60000L;
		}else{
			this.waitPromptTime = waitPromptTime;
		}
	}

	/*
	private String getSymbolPartingStr(){
		byte[] b = new byte[1];
		b[0] = (byte)symbolParting;
		String str = new String(b);
		return str;
	}
	*/

	public String getUsernamePrompt() {
		return usernamePrompt;
	}

	public void setUsernamePrompt(String usernamePrompt) {
		this.usernamePrompt = usernamePrompt;
	}

	public String getPasswordPrompt() {
		return passwordPrompt;
	}

	public void setPasswordPrompt(String passwordPrompt) {
		this.passwordPrompt = passwordPrompt;
	}

	public String getLoginOkPrompt() {
		return loginOkPrompt;
	}

	public void setLoginOkPrompt(String loginOkPrompt) {
		this.loginOkPrompt = loginOkPrompt;
	}

	public long getNeId() {
		return neId;
	}

	public void setNeId(long neId) {
		this.neId = neId;
	}

	public AtomicBoolean getTimeOutFlag() {
		return timeOutFlag;
	}

	public void setTimeOutFlag(AtomicBoolean timeOutFlag) {
		this.timeOutFlag = timeOutFlag;
	}

	public void setStatus(int status) {
		this.status = status;
	}



	public long getReportCacheCapacity() {
		return reportCacheCapacity;
	}

	public void setReportCacheCapacity(long reportCacheCapacity) {
		this.reportCacheCapacity = reportCacheCapacity;
	}





}
