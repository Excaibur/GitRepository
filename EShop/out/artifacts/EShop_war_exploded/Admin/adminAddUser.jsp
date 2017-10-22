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
    <script type="text/javascript" src="../js/chkUser.js"></script>
</head>
<body>
<form id="regform" action="/manage?action=add" method="post">
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr height="40">
        <td colspan="2" class="itemTitle" align="center">
        	新增管理员
        </td>
      </tr>
      <tr height="30">
        <td width="160" align="right">管理员真实姓名：</td>
        <td><input type="text" name="adminName" size="41" styleClass="textBox"/></td>
     </tr>
      <tr height="30">
        <td valign="top"  align="right">帐 号：</td>
        <td><input type="text" name="loginName" size="41" styleClass="textBox" onblur="chkLoginName()"/><span id="loginName"></span></td>
     </tr>
      <tr height="30">
        <td valign="top"  align="right">密 码：</td>
        <td><input type="text" name="loginPwd" size="41" styleClass="textBox"/></td>
     </tr>
      <tr height="30">
        <td valign="top"  align="right">管理员类型：</td>
        <td>
        	<select name="adminType" styleClass="textBox">
	        	<option value="1">商品管理员</option>
	        	<option value="2">订单管理员</option> 
	        	<option value="3">会员管理员</option> 
	        	<option value="4">系统管理员</option> 	        		        		        	        	
        	</select>
        </td>
     </tr>          
      <tr height="30">
        <td colspan="2" align="center">
			<input type="reset" value="重填">
			<input type="submit" value="提交" onclick="chkAddUser()">
		</td>
     </tr>
      
    </table>
</form>
</body>
</html>