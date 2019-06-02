<%@ page contentType="text/html; charset=gb2312" language="java"
import="java.util.*,com.boco.eoms.filemanage.*,com.boco.eoms.filemanage.appinfo.*,
java.io.File,com.boco.eoms.attachment.dao.TawAttachmentDAO,
com.boco.eoms.attachment.model.TawAttachment,com.boco.eoms.common.util.StaticMethod,
com.boco.eoms.db.util.ConnectionPool,com.boco.eoms.gzjh.util.gzUtil" errorPage="" %>

<html>
<head>
<title>�ļ��ϴ�����ҳ��</title>
<script language="JavaScript">
<!---
  function onDel(idlist)
  {
    var temp = "";

    if(document.filename.files.length != null)
    {
      for(var i=0;i<document.filename.files.length;i++)
      {
        if(document.filename.files[i].checked)
        {
          temp = temp + "," + document.filename.files[i].value;
        }
      }
    }
    else
    {
      if(document.filename.files.checked)
      {
        temp = temp + "," + document.filename.files.value;
      }
    }

    if(temp.length==0)
    {
      alert("��ѡ��Ҫɾ�����ļ�");
      return;
    }

    window.navigate( "do_remove.jsp?app=<%=request.getParameter("app")%>&idfile=<%=request.getParameter("idfile")%>&filelist=" + idlist + "&removeid=" + temp.substring(1,temp.length));
  }
--->
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/upload.css" type="text/css">
</head>
<body>
<%
String fileIdList = "";
String[] fileIds;

fileIdList = request.getParameter("filelist");
System.out.println("upload_fileIdList="+fileIdList);
fileIdList = fileIdList == null?"":fileIdList;
ConnectionPool ds=com.boco.eoms.db.util.ConnectionPool.getInstance();
TawAttachmentDAO tawAttachmentDAO = new TawAttachmentDAO(ds);
AppInformations appInformations = AppInformations.getInstance();
AppInformation aAppInformation = null;

// ����Request�����ȡ����ֵ֮
if(request.getParameter("app") != null)
{
aAppInformation = appInformations.get(request.getParameter("app"));

// �½�һ��SmartUpload����
SmartUpload su = new SmartUpload();

// �ϴ���ʼ��
su.initialize(pageContext);

su.setMaxFileSize(aAppInformation.getSize());
System.out.println("aAppInformationSize="+aAppInformation.getSize());
su.setAllowedFilesList(aAppInformation.getAllowedFile());
System.out.println("aAppInformationAllowedFile="+aAppInformation.getAllowedFile());
su.setDeniedFilesList(aAppInformation.getDeniedFile());
System.out.println("aAppInformationDeniedFile="+aAppInformation.getDeniedFile());
System.out.println("aAppInformationPath="+aAppInformation.getPath());

// �ϴ��ļ�
try
{
  su.upload();
}
catch(Exception e)
{
  //out.println(e.getMessage()+"<br>");
}

// ���ϴ��ļ�ȫ�����浽ָ��Ŀ¼

int count = su.save(aAppInformation.getPath(),1);

// ��һ��ȡ�ϴ��ļ���Ϣ��ͬʱ�ɱ����ļ���

for (int i=0;i<su.getFiles().getCount() ;i++)
{

  com.boco.eoms.filemanage.File file = su.getFiles().getFile(i);

if (file.isMissing()) continue;

String tempfilename = file.getFileName().substring(0,file.getFileName().indexOf("."))+ gzUtil.getCurrentDateTime("yyyyMMddHHmmss") + "."+file.getFileExt();
int attId = tawAttachmentDAO.insert(StaticMethod.getString(file.getFileName()),StaticMethod.getString(tempfilename),file.getSize(),"admin","",Integer.parseInt(aAppInformation.getAppId()));

file.saveAs(aAppInformation.getPath()+ File.separator +  tempfilename,su.SAVE_VIRTUAL,aAppInformation);
file.romove(aAppInformation.getPath()+ File.separator + file.getFileName(),1);
fileIdList = fileIdList.trim().equals("")?String.valueOf(attId):(fileIdList + "," + attId);
}

List list = null;

if( fileIdList!=null)
{
fileIds = fileIdList.split(",");
request.setAttribute("filelist",fileIdList);
}
else
{
request.setAttribute("filelist","");
}

if(!fileIdList.equals(""))
{
  list = tawAttachmentDAO.list(fileIdList);
}

if(list != null)
{
TawAttachment tawAttachment = null;
%>
<form name="filename">
<%
for(int i=0; i<list.size(); i++)
{
  tawAttachment = (TawAttachment)list.get(i);
%>
  <input type='checkbox' name='files' value='<%=tawAttachment.getAttachmentId()%>'>
  <a href='do_download.jsp?app=<%=aAppInformation.getAppCode()%>&fileid=<%=tawAttachment.getAttachmentId()%>'>
    <%=StaticMethod.dbNull2String(tawAttachment.getAttachmentName())%>
  </a>
<%
}
%>
</form>
<%
}
}
%>

<FORM METHOD="POST" ACTION="do_upload.jsp?app=<%=aAppInformation.getAppCode()%>&filelist=<%=fileIdList%>&idfile=<%=request.getParameter("idfile")%>" ENCTYPE="multipart/form-data">
  <div>
    <input type="FILE" name="FILE1" size="10" >
    <input type="submit" name="Submit" value="�ϴ�" class="clsBtn">
    <input type="button" name="del" value="ɾ��" onclick="onDel('<%=fileIdList%>')" class="clsBtn">
  </div>
</FORM>

<script language="javascript">
<!--
parent.document.forms[0].<%=request.getParameter("idfile")%>.value = "<%=fileIdList%>";
-->
</script>

</body>
</html>

