<%@ page contentType="text/html; charset=GB2312" %>
<html>

<head>
<title>�ļ��ϴ�</title>
</head>


<body>

<p align="center">�ļ��ϴ�</p>
  <FORM METHOD="POST" ACTION="do_upload.jsp?app=worksheet" ENCTYPE="multipart/form-data">
  <input type="hidden" name="TEST" value="worksheet">
    <table width="75%" border="1" align="center">
      <tr>
        <td>
          <div align="center">1��
          <input type="FILE" name="FILE1" size="30">
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">2��
          <input type="FILE" name="FILE2" size="30">
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">3��
          <input type="FILE" name="FILE3" size="30">
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">4��
          <input type="FILE" name="FILE4" size="30">
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">
          <input type="submit" name="Submit" value="�ϴ�����">
          </div>
        </td>
      </tr>
    </table>
  </FORM>
</body>
</html>





