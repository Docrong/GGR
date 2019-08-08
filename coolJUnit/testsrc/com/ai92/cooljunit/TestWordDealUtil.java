package com.ai92.cooljunit;

import static org.junit.Assert.*;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

public class TestWordDealUtil {

    //����wordFormat4DB һ��Ĵ������
    @Test
    public void wordFormat4DBNormal() {
        String target = "employeeInfo";
        String result = WordDealUtil.wordFormat4DB(target);

        assertEquals("employee_info", result);
    }

    //����nullʱ�Ĵ������
    @Test
    public void wordFormat4DBNull() {
        String target = null;
        String result = WordDealUtil.wordFormat4DB(target);

        assertNull(result);
    }

    //���Կ��ַ���ʱ�Ĵ������
    @Test
    public void wordFormat4DBEmpty() {
        String target = "";
        String result = WordDealUtil.wordFormat4DB(target);

        assertEquals("", result);
    }

    //���Ե�����ĸ��дʱ�����
    @Test
    public void wordFormat4DBegin() {
        String target = "EmployeeInfo";
        String result = WordDealUtil.wordFormat4DB(target);

        assertEquals("employee_info", result);
    }

    //���Ե�β��ĸΪ��дʱ�����
    @Test
    public void wordFormat4DBEnd() {
        String target = "employeeInfoA";
        String result = WordDealUtil.wordFormat4DB(target);

        assertEquals("employee_info_a", result);
    }

    //���Զ��������ĸ��дʱ�����
    @Test
    public void wordFormat4DBTogether() {
        String target = "employeeAInfo";
        String result = WordDealUtil.wordFormat4DB(target);

        assertEquals("employee_a_info", result);
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestWordDealUtil.class);
    }

}
