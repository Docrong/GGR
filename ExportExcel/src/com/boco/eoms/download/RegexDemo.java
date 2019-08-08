package com.boco.eoms.download;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexDemo {


    public static void main(String[] args) {
        /*
         * ��Ҫ�ѡ�select ID as ���, TITLE as ���� from table_a��
         * ������sql�����asǰ��ID��TITLEȡ��������string����A���棬
         * as����ı�źͱ������string����B���棬
         */
        String input = "select * from (select b.*, rownum rn from (select ID as ID, SHEETID as ������, TITLE as ����, SHEETACCEPTLIMIT as ����ʱ��, SHEETCOMPLETELIMIT as ����ʱ��, SENDTIME as �ɵ�ʱ��, SENDUSERID as �ɵ���  from commonfault_main) b where rownum <= 10) WHERE rn >= 0";
        String regex = "\\w+(?=\\s+as\\s+)";//ƥ��as֮ǰ���ֶ�
        String regex2 = "(?<=\\s{1,100}as\\s{1,100})[\\w[\u4E00-\u9FA5]]+";//ƥ��as֮����ֶ�
        String[] arrA = matcherWorld(input, regex);
        String[] arrB = matcherWorld(input, regex2);
        System.out.println("as֮ǰ���ֶ�Ϊ��" + arrA[0] + "��" + arrA[1] + "��" + arrA[2]);
        System.out.println("as֮����ֶ�Ϊ��" + arrB[0] + "��" + arrB[1] + "��" + arrB[2]);


    }

    /**
     * ƥ�亯��
     *
     * @param input
     * @param regex
     * @return
     */
    public static String[] matcherWorld(String input, String regex) {
        //CASE_INSENSITIVE��ʾ�����ִ�Сд
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        StringBuffer result = new StringBuffer();
        while (m.find()) {
            result.append(m.group() + ",");
        }
        return result.toString().split(",");
    }


}