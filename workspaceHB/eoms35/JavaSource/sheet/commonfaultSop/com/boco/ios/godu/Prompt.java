package com.boco.ios.godu;


import java.util.regex.Pattern;

/**
 * 期望值生成器
 * (公共类: 支持GODU、NeTelnetD)
 *
 * @author 闫保亮
 */
public class Prompt {


    /**
     * 支持GODU:命令执行成功提示符
     */
//	public static final String GODU_SEND_CMD_END = "BOCOGODUCMDEND";
    public static final String GODU_SEND_CMD_END = "<";//爱立信
    /**
     * 支持GODU:开始连接网元后的提示符
     */
//	public static final String GODU_CONN_NE_START = "BOCOGODUACKSTART";
    public static final String GODU_CONN_NE_START = "<";//爱立信
    /**
     * 支持GODU:结束连接网元后的提示符
     */
    public static final String GODU_CONN_NE_END = ">";//爱立信
    /**
     * 支持GODU:进入到统一平台后的提示符
     */
    public static final String GODU_CONN_GODU_START = "[GODU-CMD]";
    /**
     * 支持GODU:命令执行超时提示符
     */
    public static final String GODU_SEND_CMD_TIMEOUT = "BOCOGODUCMDTIMEOUTEND";
    /**
     * 支持GODU:命令执行完成提示符
     */
//	public static final String GODU_SEND_CMD_SUCCESS = "BOCOGODUCMDSUCCESSEND";
    public static final String GODU_SEND_CMD_SUCCESS = "<";
    /**
     * 支持GODU:命令执行错误提示符
     */
//	public static final String GODU_SEND_CMD_WRONG = "BOCOGODUCMDWRONGEND";
    public static final String GODU_SEND_CMD_WRONG = "<";


    /**
     * 支持NeTelnetD:命令执行成功提示符
     */
    public static final String NETELNETD_SEND_CMD_END = "NETELNETD_SUCCESS";
    /**
     * 支持NeTelnetD:开始连接网元后的提示符
     */
    public static final String NETELNETD_CONN_NE_START = "";
    /**
     * 支持NeTelnetD:结束连接网元后的提示符
     */
    public static final String NETELNETD_CONN_NE_END = "";
    /**
     * 支持NeTelnetD:命令执行超时提示符
     */
    public static final String NETELNETD_SEND_CMD_TIMEOUT = "NETELNETD_FAILD";
    /**
     * 支持NeTelnetD:命令执行完成提示符
     */
    public static final String NETELNETD_SEND_CMD_SUCCESS = "NETELNETD>";
    /**
     * 支持NeTelnetD:命令执行错误提示符
     */
    public static final String NETELNETD_SEND_CMD_WRONG = "";


    //连接成功并设置命令结束成功


    /**
     * 生成器
     *
     * @param promptStr 原期望值字串（如：$,&,>）
     * @return 生成的期望值字串
     * @throws Exception
     */
    public static String builder(String promptStr) {
        StringBuffer rePrompt = new StringBuffer("");
        if (promptStr.trim().length() == 0) {
            return rePrompt.toString();
        }
        String[] arrPrompt = promptStr.trim().split(",");
        for (int i = 0; i < arrPrompt.length; i++) {
            String p = arrPrompt[i];

            if (!checkMatcherOK(p)) {
                p = specialCharactersConvert(p);
            }
            rePrompt.append("end===" + p + "  ");
        }
        return rePrompt.toString();
    }

    /**
     * 特殊字符转换
     *
     * @param str
     * @return
     */
    private static String specialCharactersConvert(String str) {
        //		点的转义：.   ==> \\u002E
        //		美元符号的转义：$   ==> \\u0024
        //		乘方符号的转义：^   ==> \\u005E
        //		左大括号的转义：{   ==> \\u007B
        //		左方括号的转义：[   ==> \\u005B
        //		左圆括号的转义：(   ==> \\u0028
        //		竖线的转义：| ==> \\u007C
        //		右圆括号的转义：) ==> \\u0029
        //		星号的转义：*   ==> \\u002A
        //		加号的转义：+   ==> \\u002B
        //		问号的转义：?   ==> \\u003F
        //		反斜杠的转义：\ ==> \\u005C

        str = str.replaceAll("\\\\", "\\\\\\\\");
        str = str.replaceAll("\\.", "\\\\.");
        str = str.replaceAll("\\$", "\\\\$");
        str = str.replaceAll("\\^", "\\\\^");
        str = str.replaceAll("\\{", "\\\\{");
        str = str.replaceAll("\\[", "\\\\[");
        str = str.replaceAll("\\(", "\\\\(");
        str = str.replaceAll("\\|", "\\\\|");
        str = str.replaceAll("\\)", "\\\\)");
        str = str.replaceAll("\\*", "\\\\*");
        str = str.replaceAll("\\+", "\\\\+");
        str = str.replaceAll("\\?", "\\\\?");


        return str;
    }

    /**
     * 检查是否是合法正则
     *
     * @param str
     * @return
     */
    private static boolean checkMatcherOK(String str) {
        try {
            Pattern pattern = Pattern.compile(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static void main(String[] args) {
        String ss = "\\$";
        System.out.println(builder(ss));

    }

}
