<%@ page contentType="text/html; charset=utf-8" %>

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
    <script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="../js/jquery-validate.js"></script>
    <script type="text/javascript" src="../js/chkCate.js"></script>
</head>
<body>

<form id="cateform" action="/ptype?action=add" method="post">
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr height="40">
        <td colspan="2" class="itemTitle" align="center">
        	新增商品分类
        </td>
      </tr>
      <tr height="30">
        <td width="160" align="right">商品分类名称：</td>
        <td><input type="text" name="cateName" size="41" styleClass="textBox" onblur="chkCateName()"/>
            <span id="cateName"></span> </td>
     </tr>
      <tr height="30">
        <td valign="top"  align="right">商品分类描述：</td>
          <td><textarea name="cateDesc" cols="40" rows="10" styleClass="textBox"></textarea></td>
     </tr>
      <tr height="30">
        <td colspan="2" align="center">
			<input type="reset" value="重填">
			<input type="submit" value="提交" onclick="chkAddCate()">
		</td>
     </tr>
     
    </table>
</form>
</body>
</html>