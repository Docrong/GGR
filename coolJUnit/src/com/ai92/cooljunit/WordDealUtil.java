package com.ai92.cooljunit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �����ơ���ַ���ַ�����ʽ�����ݽ��и�ʽ���
 * ���߸�ʽ���Ĺ�����
 *
 * @author Ai92
 */
public class WordDealUtil {

    /**
     * ��Java�������ƣ�ÿ�����ʵ�ͷ��ĸ��д������
     * ���ݿ�������ϰ�߽��и�ʽ��
     * ��ʽ���������ΪСд��ĸ������ʹ���»��߷ָ���������
     * �������nameΪnull���򷵻�null
     * <p>
     * ���磺employeeInfo ������ʽ��֮���Ϊ employee_info
     *
     * @param name Java��������
     */
    public static String wordFormat4DB(String name) {

        if (name == null) {
            return null;
        }

        Pattern p = Pattern.compile("[A-Z]");
        Matcher m = p.matcher(name);
        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            if (m.start() != 0)
                m.appendReplacement(sb, ("_" + m.group()).toLowerCase());
        }
        return m.appendTail(sb).toString().toLowerCase();
    }
}
