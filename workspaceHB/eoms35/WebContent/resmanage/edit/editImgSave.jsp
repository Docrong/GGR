<%@page contentType="text/html;charset=gb2312"%>

<%@page import="java.util.*,java.io.File"%>

<%@page import="com.boco.eoms.resmanage.jspsmart.upload.*"%>

<%@page import="com.boco.eoms.resmanage.entity.*"%>

<%@page import="mcs.common.db.*"%>

<%@page  import="com.boco.eoms.common.util.*"%>

<%@include file="../power.jsp"%>

<jsp:useBean id="myUpload" scope="page" class="com.boco.eoms.resmanage.jspsmart.upload.SmartUpload" />

<%

request.setCharacterEncoding("GBK");

RoomOpt roomopt = new RoomOpt();

Vector roomImgVec = new Vector();

myUpload.initialize(pageContext);

myUpload.upload();



String sId = null;

if(request.getParameter("id") != null)

	sId = request.getParameter("id");

else

	sId = "2";

String pi_id = myUpload.getRequest().getParameter("pi_id");

String tabid = myUpload.getRequest().getParameter("tabid");

String fi_deviceclass = myUpload.getRequest().getParameter("id");



String cityId = null;

cityId = roomopt.getCityById(fi_deviceclass,pi_id);



String cc_memo = StaticMethod.dbNull2String(myUpload.getRequest().getParameter("cc_memo"));

int count=0;

String separator=File.separator;

//String uploaddir=realPath + "resmanage" + separator + "upload" + separator;

String uploaddir="resmanage" + separator + "upload" + separator;
//out.println("uploaddir is:::"+uploaddir);


try{

//上载文件

  for (int i=0;i<myUpload.getFiles().getCount();i++) 

	  {

		com.boco.eoms.resmanage.jspsmart.upload.File file = myUpload.getFiles().getFile(i);

		//String fileName2 = StaticMethod.dbNull2String(file.getFileName());

		String fileName2 = file.getFileName();

		if (!file.isMissing())

			{

				file.saveAs(uploaddir + fileName2);

				//String cc_pic = uploaddir + file.getFileName();fileName2

				String cc_pic = uploaddir + fileName2;

				/*out.println("FieldName = " + file.getFieldName() + "<BR>");

				out.println("Size = " + file.getSize() + "<BR>");

				out.println("FileName = " + file.getFileName() + "<BR>");

				out.println("FileExt = " + file.getFileExt() + "<BR>");

				out.println("FilePathName = " + file.getFilePathName() + "<BR>");

				out.println("ContentType = " + file.getContentType() + "<BR>");

				out.println("ContentDisp = " + file.getContentDisp() + "<BR>");

				out.println("TypeMIME = " + file.getTypeMIME() + "<BR>");

				out.println("SubTypeMIME = " + file.getSubTypeMIME() + "<BR>");

				*/

				count++;

				roomImg roomimg = new roomImg();

				roomimg.setFi_device(Integer.parseInt(pi_id));

				roomimg.setFi_city(cityId);

				roomimg.setFi_deviceclass(Integer.parseInt(fi_deviceclass));

				roomimg.setCc_pic(cc_pic);

				roomimg.setCc_memo(cc_memo);

				roomImgVec.addElement(roomimg);

				int row = roomopt.roomImgOpt(roomImgVec);

				//out.println("row is: "+row);

			}

		

	 }

} catch (Exception e) 

	{

        out.println(e.toString());

    }

	//out.println(count + " file(s) uploaded.");

%>

<%

//out.println("count is:"+count);

String retpage = null;

if(count >0)

	retpage = "typeSelect.jsp";

else

	retpage = "editImgInsert.jsp";

%>

 <body onload="returnInput()">

   <form action="<%=retpage%>" method=POST name=editSaveForm>

	<input type="hidden" name="pi_id" value=<%=pi_id%>></input>

	<input type="hidden" name="id" value=<%=sId%>></input>

	<input type="hidden" name="tabid" value=<%=tabid%>></input>

	</form>

	</body>

 <script>

function returnInput()

{

	var count=<%=count%>;

	if (count>0)

		alert("图片上传成功！");

	else

		alert("图片上传失败！");

	editSaveForm.submit();

}

</script>