<%@ page contentType="text/html; charset=gb2312" %>

<html>
<head>
<title>�����̳�ϵͳ--��̨����</title>
<link href="../CSS/stylesheet.css" rel="stylesheet" type="text/css">

<style type="text/css">
	<!--
	body {
		background-color: lightgrey;
	}
	-->
</style>
</head>
<body>

<form action="" enctype="multipart/form-data">
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr height="40">
        <td colspan="2" class="itemTitle" align="center">
        	�ؼ���Ʒ����
        </td>
      </tr>
      <tr height="24">
        <td width="160" align="right">��Ʒ���</td>
        <td><input type="text" name="category" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right">��Ʒ���ƣ�</td>
        <td><input type="text" name="merName" size="41" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td width="160" align="right">��Ʒ�ͺţ�</td>
        <td><input type="text"  name="merModel" size="41" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right">��ƷͼƬ��</td>
        <td> <img id="picture" src="../images/default.jpg" height="80" width="80" border="1"/></td>
     </tr>
      <tr height="24">
        <td width="160" align="right">�г��ۣ�</td>
        <td><input type="text"  name="price" size="41" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right">�����ؼۣ�</td>
        <td>
			<input type="radio" name="special" value="0"/>��&nbsp;&nbsp;
            <input type="radio" name="special" value="1"/>��
		</td>
     </tr>
      <tr height="24">
        <td width="160" align="right">�� �ۣ�</td>
        <td><input type="text" name="sprice" size="41" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right">��Ʒ������</td>
        <td><textarea property="merDesc" cols="40" rows="10" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td width="160" align="right">�������ң�</td>
        <td><input type="text" name="manufacturer" size="41" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right">�������ڣ�</td>
        <td><input type="text" name="leaveFactoryDate" size="41" readonly="true" styleClass="textBox"/></td>
     </tr>	 	 	 	 
      <tr height="24">
        <td colspan="2" align="center">
			<a href="">
				����
			</a>
		</td>
     </tr>
    </table>
</form>
	
</body>
</html>