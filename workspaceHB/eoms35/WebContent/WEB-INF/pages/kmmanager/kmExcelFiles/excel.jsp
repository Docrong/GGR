<%/*
˵������Ӧweblogic�����أ��޸�������ȡ��
@ page contentType="text/html;charset=gb2312"
import="com.boco.eoms.gzjh.util.*,com.boco.eoms.common.util.StaticMethod"
String excelfile = (String)request.getAttribute("excelfile");
String excelfilename = (String)request.getAttribute("excelfilename");
	SmartUpload su = new SmartUpload();
	su.initialize(pageContext);
	su.setContentDisposition(null);

	su.downloadFile(excelfile,"",excelfilename);
*/%><%@ page contentType="text/html;charset=gb2312" import="com.jspsmart.upload.*" %><%
String excelfile = (String)request.getAttribute("excelfile");
String excelfilename = (String)request.getAttribute("excelfilename");
// �½�һ��SmartUpload����
SmartUpload su = new SmartUpload();
// ��ʼ��
su.initialize(pageContext);
// �趨contentDispositionΪnull�Խ�ֹ������Զ����ļ���
//��֤������Ӻ��������ļ��������趨�������ص��ļ���չ��Ϊ
//docʱ����������Զ���word��������չ��Ϊpdfʱ��
//���������acrobat�򿪡�
su.setContentDisposition(null);
// �����ļ�
su.downloadFile(excelfile,"",excelfilename);
%>