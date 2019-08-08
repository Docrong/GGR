<%@ page import="com.boco.eoms.resmanage.regex.*" %>
<%
    Pattern p = null; //������ʽ
    Matcher m = null; //�������ַ���
    boolean b;
    String s = null;
    StringBuffer sb = null;
    int i = 0;
    //�ַ���ƥ�䣬���ǲ����ϵ�
    p = Pattern.compile("a*b");
    m = p.matcher("baaaaab");
    b = m.matches();
    out.println(b + "<br>");
%>