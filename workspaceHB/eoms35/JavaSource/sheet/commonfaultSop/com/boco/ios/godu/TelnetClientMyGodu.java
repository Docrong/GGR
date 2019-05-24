package com.boco.ios.godu;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.telnet.TelnetClient;
import org.apache.log4j.Logger;

import com.boco.eoms.commons.loging.BocoLog;

public class TelnetClientMyGodu {

	private TelnetClient telnet = new TelnetClient();
	/** 客户端输入 */
	private InputStreamReader in;
	/** 客户端输出 */
	private PrintWriter out;
	/** 原始报告缓冲容量 */
	private long reportCacheCapacityClient = 102400;
	/** 读取期望值时延(频率*5ms) */
	private int readRate = 100;


	/**
	 * 连接服务端
	 * @param host 主机
	 * @param port 端口
	 * @throws SocketException
	 * @throws IOException
	 */
	public void connect(String host, int port) throws SocketException, IOException {
		telnet.connect(host, port);
		in = new InputStreamReader(telnet.getInputStream());
		out = new PrintWriter(telnet.getOutputStream());
	}

	/**
	 * 向输出流写入字符
	 * @param str 写入输出流的字符
	 * @throws Exception 向输出流写入字符出错
	 */
	protected void write(String str) throws Exception {
		try {
			String sendCmdStr = str;	//命令行
			boolean enterTag = true;	//发命令后是否按回车
			byte[] cmdByte = str.getBytes();

			Matcher m = null;
	        m = Pattern.compile("^(.+)无回车$").matcher(str);
	        if(m.find()){
	        	sendCmdStr = m.group(1);
	        	enterTag = false;
	        }
	        m = Pattern.compile("^(.+)切编码(.+)$").matcher(str);
	        if(m.find()){
	        	String s1 = m.group(1);
	        	String s2 = m.group(2);
	        	sendCmdStr = s1;
	        	switchInputStreamCoding(s2);
	        }
	        m = Pattern.compile("^(.+)调延时(\\d+)$").matcher(str);
	        if(m.find()){
	        	String s1 = m.group(1);
	        	String s2 = m.group(2);
	        	sendCmdStr = s1;
	        	readRate = Integer.parseInt(s2);
	        }
			if(enterTag){
				out.println(sendCmdStr);
			}else{
				out.print(sendCmdStr);
			}
			out.flush();
			BocoLog.debug(TelnetClientMyGodu.class,"向输出流写入字符:" + sendCmdStr);
		} catch (Exception e) {
			throw new Exception("向输出流写入字符出错", e);
		}
	}
	/**
	 * 发送命令,并等待收取结果
	 * @param cmd 命令
	 * @param prompts 命令操作成功的提示符
	 * @param waitPromptTime 等待提示符的最长时间
	 * @param timeOutFlag 命令操作成功提示符
	 * @return 命令执行结果
	 * @throws Exception
	 */
	public String send(String cmd, String[] prompts,long waitPromptTime,AtomicBoolean timeOutFlag) throws Exception {
		try {
			write(cmd);
			return readUntil(prompts,waitPromptTime,timeOutFlag);
		} catch (Exception e) {
			throw new Exception("向服务端发送指令" + cmd + "出错", e);
		}
	}

	/**
	 * 读取输入流直至指定提示符,如果超过最长等待时间，返回取到的结果信息
	 * @param prompts 提示符（不支持正则）
	 * @param waitPromptTime 等待期望值时间
	 * @param timeOutFlag 是否超时
	 * @return 读取到的输入流字符（从对象：timeOutFlag中可知是否超时）
	 * @throws Exception 读取输入流直至指定提示符出错
	 */
	protected String readUntil(String[] prompts,long waitPromptTime,AtomicBoolean timeOutFlag) throws Exception {
		long currentTime = System.currentTimeMillis();
		timeOutFlag.set(false);
		StringBuilder promptStrBdr = new StringBuilder();
		//处理期望值（不支持正则）
		for(int i=0;i<prompts.length;i++){
			prompts[i] = parsePrompt(prompts[i]);
			if(i == prompts.length-1){
				promptStrBdr.append(prompts[i]);
			}else{
				promptStrBdr.append(prompts[i]).append(" 或 ");
			}
		}
		BocoLog.debug(TelnetClientMyGodu.class,"期待结束提示符（等待" + waitPromptTime + "毫秒）:" + promptStrBdr.toString());

		try {
			// 提示符的最后一个字符
			String[] promptStringArr = prompts;
			char[] lastCharArr = null;
			lastCharArr = new char[promptStringArr.length];

			for(int i=0;i<promptStringArr.length;i++){
				if(promptStringArr[i].length()>0){
					char lastChar = promptStringArr[i].charAt(promptStringArr[i].length() - 1);
					lastCharArr[i]=lastChar;
				}else{
					char lastChar = (char)26080;		// (char)26080 = "无"
					lastCharArr[i]=lastChar;
				}
			}
			StringBuffer sb = new StringBuffer();
			int readyFalseCount = 0;
			char ch;
			int currentReadRate = readRate;
			readRate = 100;
			while (true) {
				if(in.ready()){
					currentTime = System.currentTimeMillis();
					ch = (char) in.read();
					// 保存至缓存
					sb.append(ch);
					if(sb.length() > reportCacheCapacityClient){
						sb.delete(0, 1);
					}
					readyFalseCount = 0;
				}else{
					readyFalseCount++;
					//当好长时间没有字符流返回
					if(readyFalseCount>currentReadRate){
						for(int i=sb.toString().length()-1;i>=0;i--){
							ch = (char)sb.charAt(i);
							String subStrEnd = sb.toString().substring(0, i+1);
							// 判断是否接收到提示符的最后一个字符
							for(int j=0;j<lastCharArr.length;j++){
								if(ch == lastCharArr[j]){
									if (subStrEnd.endsWith(promptStringArr[j])) {
										String str = clearResultGoduStr(sb);
										if(promptStringArr[j].equals(Prompt.GODU_SEND_CMD_TIMEOUT)){
											timeOutFlag.set(true);
											BocoLog.debug(TelnetClientMyGodu.class,"读取来自输入流的字符（已超时）：" + str);
										}else{
											BocoLog.debug(TelnetClientMyGodu.class,"读取来自输入流的字符（未超时）：" + str);
										}
										sb = null;
										return str;
									}
								}
							}
//							//回车
//							if(ch == (char)13){
//								continue;
//							}
//							//换行
//							if(ch == (char)10){
//								continue;
//							}
							//不可见字符
							if((int)ch < 32 ||(int)ch > 127){
								continue;
							}
							//空格
							if(ch != (char)32){
								break;
							}

						}
					}
					Thread.sleep(5);
				}
				//判断是否超时（在没有任何输出时才开始计时）
				if(System.currentTimeMillis() - currentTime > waitPromptTime){
					String str = clearResultGoduStr(sb);
					BocoLog.debug(TelnetClientMyGodu.class,"读取来自输入流的字符（已超时" + waitPromptTime + "毫秒）：" + str);
					sb = null;
					timeOutFlag.set(true);
					return str;
				}
			}
		} catch (Exception e) {
			throw new Exception("读取输入流等待指定提示符（" + promptStrBdr.toString() + "）出错", e);
		}
	}
	/**
	 * 清除统一指令平台多余的输出
	 * @param result 统一指令平台返回的结果
	 * @return 清除后的结果
	 */
	private String clearResultGoduStr(StringBuffer result){
		String newResult = "";
		if(result == null){
			return newResult;
		}

		String XML_START = "BOCOGODUACKSTART";
//		String XML_END = "BOCOGODUACKEND";
                String XML_END = "<"; //modify by luoyi 爱立信登陆网元成功返回最后提示符
		String CMD_RETURN_END = "BOCOGODUCMDEND";
		String CMD_SUCCESS_RETURN_END = "BOCOGODUCMDSUCCESSEND";
		String CMD_WRONG_RETURN_END = "BOCOGODUCMDWRONGEND";


		// 提示符的最后一个字符
		String[] promptStringArr = new String[]{XML_START,XML_END,CMD_RETURN_END,CMD_SUCCESS_RETURN_END,CMD_WRONG_RETURN_END};
		char[] lastCharArr =  new char[promptStringArr.length];
		for(int i=0;i<promptStringArr.length;i++){
			if(promptStringArr[i].length()>0){
				char lastChar = promptStringArr[i].charAt(promptStringArr[i].length() - 1);
				lastCharArr[i]=lastChar;
			}else{
				char lastChar = (char)26080;		// (char)26080 = "无"
				lastCharArr[i]=lastChar;
			}
		}


		for(int i=result.length()-1;i>=0;i--){
			char ch = (char)result.charAt(i);
			String subStrEnd = result.substring(0, i+1);
			// 判断是否接收到提示符的最后一个字符
			for(int j=0;j<lastCharArr.length;j++){
				if(ch == lastCharArr[j]){
					if (subStrEnd.endsWith(promptStringArr[j])) {
						newResult = result.substring(0, i+1-promptStringArr[j].length());
						return newResult;
					}
				}
			}
			//不可见字符
			if((int)ch < 32 ||(int)ch > 127){
				continue;
			}
			//空格
			if(ch != (char)32){
				break;
			}

		}

		newResult = result.toString();
		return newResult;
	}

	/**
	 * 读取输入流直至指定提示符,如果超过最长等待时间，返回取到的结果信息
	 * @param prompt 提示符（不支持正则）
	 * @param waitPromptTime 等待期望值时间
	 * @param timeOutFlag 是否超时
	 * @return 读取到的输入流字符（从对象：timeOutFlag中可知是否超时）
	 * @throws Exception 读取输入流直至指定提示符出错
	 */
	protected String readUntil(String promptStr,long waitPromptTime,AtomicBoolean timeOutFlag) throws Exception{
		return readUntil(new String[]{promptStr},waitPromptTime,timeOutFlag);
	}


	/**
	 * 处理转义字符
	 * @param prompt 原始提示符
	 * @return 支持转义字符的提示符
	 */
	private String parsePrompt(String prompt) {
		return prompt.replace("\\n", "\n");
	}

	/**
	 * 断开连接
	 */
	public void disconnect() {
		try {
			if(telnet != null && telnet.isConnected()){
				in.close();
				out.close();
				telnet.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
			BocoLog.error(TelnetClientMyGodu.class,"与服务端断开连接出错" + e.getMessage());
		}
	}


	public long getReportCacheCapacityClient() {
		return reportCacheCapacityClient;
	}

	public void setReportCacheCapacityClient(long reportCacheCapacityClient) {
		this.reportCacheCapacityClient = reportCacheCapacityClient;
	}
	/**
	 * 切换输入流的编码集
	 *
	 */
	public void switchInputStreamCoding(String coding){
		InputStreamReader inBak = in;
		try {
			in = new InputStreamReader(telnet.getInputStream(),coding);
			Thread.sleep(10);
		} catch (UnsupportedEncodingException e) {
			BocoLog.error(TelnetClientMyGodu.class,"切换输入流的编码集出错" + e.getMessage());
			in = inBak;
		} catch (InterruptedException e) {
			BocoLog.error(TelnetClientMyGodu.class,"切换输入流的编码集后等待10ms出错" + e.getMessage());
		}
	}
}
