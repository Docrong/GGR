package com.ai92.cooljunit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestWordDealUtilWithParam {

    private String expected;

    private String target;

    @Parameters
    public static Collection words() {
        return Arrays.asList(new Object[][]{
                {"employee_info", "employeeInfo"},    //����wordFormat4DB һ��Ĵ������
                {null, null},                            //����nullʱ�Ĵ������
                {"", ""},                                //���Կ��ַ���ʱ�Ĵ������
                {"employee_info", "EmployeeInfo"},    //���Ե�����ĸ��дʱ�����
                {"employee_info_a", "employeeInfoA"},    //���Ե�β��ĸΪ��дʱ�����
                {"employee_a_info", "employeeAInfo"}    //���Զ��������ĸ��дʱ�����
        });
    }

    /**
     * ���������Ա���Ĺ��캯��
     *
     * @param expected �����Ĳ��Խ������Ӧ�������еĵ�һ������
     * @param target   �������ݣ���Ӧ�������еĵڶ�������
     */
    public TestWordDealUtilWithParam(String expected, String target) {
        this.expected = expected;
        this.target = target;
    }

    /**
     * ���Խ�Java�������Ƶ����ݿ����Ƶ�ת��
     */
    @Test
    public void wordFormat4DB() {
        assertEquals(expected, WordDealUtil.wordFormat4DB(target));
    }
}
