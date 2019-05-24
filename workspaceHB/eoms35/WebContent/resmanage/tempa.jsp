<%@ page import="com.boco.eoms.resmanage.regex.*"%>
<%
Pattern p=null; //正则表达式
Matcher m=null; //操作的字符串
boolean b;
String s=null;
StringBuffer sb=null;
int i=0;
 //字符串匹配，这是不符合的
p = Pattern.compile("a*b");
m = p.matcher("baaaaab");
b = m.matches();
out.println(b+"<br>");
%>