<%@ page contentType="text/html; charset=gb2312" %>

<html>
<head>
<title>电子商城系统--后台管理</title>
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
        	特价商品详情
        </td>
      </tr>
      <tr height="24">
        <td width="160" align="right">商品类别：</td>
        <td><input type="text" name="category" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right">商品名称：</td>
        <td><input type="text" name="merName" size="41" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td width="160" align="right">商品型号：</td>
        <td><input type="text"  name="merModel" size="41" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right">商品图片：</td>
        <td> <img id="picture" src="../images/default.jpg" height="80" width="80" border="1"/></td>
     </tr>
      <tr height="24">
        <td width="160" align="right">市场价：</td>
        <td><input type="text"  name="price" size="41" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right">有无特价：</td>
        <td>
			<input type="radio" name="special" value="0"/>无&nbsp;&nbsp;
            <input type="radio" name="special" value="1"/>有
		</td>
     </tr>
      <tr height="24">
        <td width="160" align="right">特 价：</td>
        <td><input type="text" name="sprice" size="41" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right">商品描述：</td>
        <td><textarea property="merDesc" cols="40" rows="10" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td width="160" align="right">生产厂家：</td>
        <td><input type="text" name="manufacturer" size="41" styleClass="textBox" readonly="true"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right">出厂日期：</td>
        <td><input type="text" name="leaveFactoryDate" size="41" readonly="true" styleClass="textBox"/></td>
     </tr>	 	 	 	 
      <tr height="24">
        <td colspan="2" align="center">
			<a href="">
				返回
			</a>
		</td>
     </tr>
    </table>
</form>
	
</body>
</html>