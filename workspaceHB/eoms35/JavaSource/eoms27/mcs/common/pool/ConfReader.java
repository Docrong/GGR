/**
 * @(#)ConfReader Copyright (c) 2001 MCS. All Rights Reserved.
 * @author Yuanlei
 * @version 1.0
 * @date 15Apr2002
 * # 此模块为连接池配置文件阅读器，用来读取XML格式的多个连接池配置信息，配置文件所在目录：[ServerContext]/pool.conf
 */
package mcs.common.pool;

import java.util.*;
import java.io.*;

import com.boco.eoms.common.util.*;

/**
 * 此模块为连接池配置文件阅读器，用来读取XML格式的多个连接池配置信息，配置文件所在目录：[ServerContext]/pool.conf<br>
 */
public class ConfReader {
    static String global = "";
    static Vector envs = new Vector();
    static PoolHash phash = new PoolHash();
    static String userDir = "";

    public ConfReader() {

    }

    /**
     * 初始化
     */
    public static boolean init() {
        userDir = System.getProperty("user.dir");
        PropertyFile prop = PropertyFile.getInstance();
        //String fileName = userDir + File.separator + "pool.conf";
        //String fileName = userDir + File.separator + "pool.conf";
        String fileName = prop.getFilePath() + File.separator + "pool.conf";
        System.out.println("fileName is:::" + fileName);
        try {
            BufferedReader br_file = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = br_file.readLine()) != null) {
                line = line.trim();
                if (line.length() == 0 || line.charAt(0) == '#') continue;
                global += line;
            }
        } catch (Exception e) {
            return false;
        }
        if (global.length() > 0) {
            //System.err.println(global);
            Vector split = splitBlock(global);
            for (int i = 0; i < split.size(); i++) {
                /**/
                System.err.println("第" + i + "个  " + (String) split.get(i));
                Env ev = getEnv((String) split.get(i));
                /**/
                System.err.println("before push " + ev.alias);
                phash.push(ev.alias, null, ev);
            }
        } else return false;


        return true;
    }

    /**
     * 把XML全文分成各个连接池具体配置的字符串块
     */
    private static Vector splitBlock(String glb) {
        String lab = "cpool";
        if (!glb.startsWith(left(lab))) return null;
        Vector strvec = new Vector();
        int start = 0;
        int end = 0;
        int i = 0;
        for (; ; ) {
            if (start == -1 || end == -1 || start >= glb.length() || end >= (glb.length() - right(lab).length())) break;
            System.err.println("第" + (i + 1) + "次截取");
            start = glb.indexOf(left(lab), end);
            end = glb.indexOf(right(lab), start);
            strvec.add(glb.substring(start + left(lab).length(), end));
            i++;
        }
        return strvec;
    }

    /**
     * 取的一个Env的实例
     */
    private static Env getEnv(String block) {
        //System.err.println(block);
        Env env = new Env();
        env.alias = getValue("alias", block);
        env.driver = getValue("driver", block);
        env.url = getValue("url", block);
        env.user = getValue("user", block);
        env.password = getValue("password", block);
        env.maxConns = Integer.parseInt(getValue("maxConns", block));
        env.minConns = Integer.parseInt(getValue("minConns", block));
        env.reaperDelay = (new Integer(getValue("reaperDelay", block))).longValue();
        env.poolTimeOut = (new Integer(getValue("poolTimeOut", block))).longValue();
        env.connTimeOut = (new Integer(getValue("connTimeOut", block))).longValue();
        env.defaultPool = getValue("defaultPool", block);
        return env;
    }

    /**
     * 从一个XML格式的字符串块中提取每一个标签所对应的字符串
     */
    private static String getValue(String key, String block) {
        int start = block.indexOf(left(key)) + left(key).length();
        int end = block.indexOf(right(key));
        return offmark(block.substring(start, end));
    }

    public static PoolHash getPoolHash() {
        return phash;
    }

    private static String left(String str) {
        return "<" + str + ">";
    }

    private static String right(String str) {
        return "</" + str + ">";
    }

    /**
     * 去除非法字符
     */
    private static String offmark(String original) {
        String rtn = "";
        char[] charValue = original.toCharArray();

        int i;
        for (i = 0; i < charValue.length; i++) {
            if (charValue[i] == '\\') {

                rtn += "\\";
                i++;
                if (i < charValue.length)
                    rtn += (new Character(charValue[i])).toString();
                continue;
            }
            rtn += (new Character(charValue[i])).toString();
        }
        return rtn;

    }
}
