package com.boco.eoms.commons.util.xml;

/**
 * ������,����ͬʱ�����û���
 *
 * @author gr
 */
public class UserCount {

    private static UserCount user = new UserCount();

    private static int count = 0;//�����û���
    private static int maxCount = 10;//��������û���

    public static UserCount getUser() {
        return user;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount() {
        count = maxCount;
    }

    public static int getMaxCount() {
        return maxCount;
    }

    public synchronized void login() {
        count++;
    }

    public synchronized void logout() {
        count--;
    }

}
