package com.boco.ios.godu;


/**
 * 网元操作器
 *
 * @author 闫保亮
 */
public interface Connection {

    /**
     * 连接指令平台
     *
     * @throws Exception
     */
    public void connect() throws Exception;

    /**
     * 按指定通道连接指令平台
     *
     * @param channelName 通道名(用户名)
     * @throws Exception
     */
    public void connect(String channelName) throws Exception;

    /**
     * 连接网元
     *
     * @param neId 网元Id
     * @return 是否连接成功
     * @throws Exception
     */
    public boolean open(long neId) throws Exception;


    /**
     * 发送命令
     *
     * @param cmd     命令
     * @param timeOut 超时时间(毫秒)
     * @param prompt  期望值
     * @return 命令执行反馈信息
     * @throws Exception
     */
    public String sendCmd(String cmd, long timeOut, String prompt) throws Exception;

    /**
     * 查看命令执行状态
     *
     * @return 命令执行状态
     */
    public int getStatus();

    /**
     * 得到默认超时时间
     *
     * @return 默认超时时间
     */
    public long getWaitPromptTime();

    /**
     * 断开连接
     */
    public void close() throws Exception;

}
