package com.boco.eoms.download;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
 
public class RegexDemo {
 
 
    public static void main(String[] args) {
        /*
         * 需要把“select ID as 编号, TITLE as 标题 from table_a”
         * 这样的sql语句中as前的ID和TITLE取出来放在string数组A里面，
         * as后面的编号和标题放在string数组B里面，
         */
        String input="select * from (select b.*, rownum rn from (select ID as ID, SHEETID as 工单号, TITLE as 标题, SHEETACCEPTLIMIT as 处理时限, SHEETCOMPLETELIMIT as 受理时限, SENDTIME as 派单时间, SENDUSERID as 派单人  from commonfault_main) b where rownum <= 10) WHERE rn >= 0";
        String regex="\\w+(?=\\s+as\\s+)";//匹配as之前的字段
        String regex2="(?<=\\s{1,100}as\\s{1,100})[\\w[\u4E00-\u9FA5]]+";//匹配as之后的字段
        String[] arrA=matcherWorld(input, regex);
        String[] arrB=matcherWorld(input, regex2);
        System.out.println("as之前的字段为："+arrA[0]+"，"+arrA[1]+"，"+arrA[2]);
        System.out.println("as之后的字段为："+arrB[0]+"，"+arrB[1]+"，"+arrB[2]);
         
         
    }
    /**
     * 匹配函数
     * @param input
     * @param regex
     * @return
     */
    public static String[] matcherWorld(String input,String regex){
        //CASE_INSENSITIVE表示不区分大小写
        Pattern p=Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m=p.matcher(input);
        StringBuffer result=new StringBuffer();
        while(m.find()){
            result.append(m.group()+",");
        }   
        return result.toString().split(",");       
    }
     
 
}