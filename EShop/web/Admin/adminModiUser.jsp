<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<form id="regform" action="/manage?action=update&id=${manage.mid}" method="post" >
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr height="40">
        <td colspan="2" class="itemTitle" align="center">
        	修改管理员
        </td>
      </tr>
      <tr height="30">
        <td width="160" align="right">管理员真实姓名：</td>
        <td><input type="text" name="adminName" size="41" styleClass="textBox" value="${manage.mname}"/></td>
     </tr>
      <tr height="30">
        <td valign="top"  align="right">帐 号：</td>
        <td><input type="text" name="loginName" size="41" styleClass="textBox" value="${manage.muser}" onblur="chkLoginName()"/></td>
     </tr>
      <tr height="30">
        <td valign="top"  align="right">密 码：</td>
        <td><input type="password" name="loginPwd" size="41" styleClass="textBox"/></td>
     </tr>
      <tr height="30">
        <td valign="top"  align="right">管理员类型：</td>
        <td>
        	<select name="adminType" styleClass="textBox">
                <c:if test="${manage.mstatus==1}">
                    <option value="1" selected>商品管理员</option>
                    <option value="2">订单管理员</option>
                    <option value="3">会员管理员</option>
                    <option value="4">系统管理员</option>
                </c:if>
                <c:if test="${manage.mstatus==2}">
                    <option value="1" >商品管理员</option>
                    <option value="2" selected>订单管理员</option>
                    <option value="3">会员管理员</option>
                    <option value="4">系统管理员</option>
                </c:if>
                <c:if test="${manage.mstatus==3}">
                    <option value="1" >商品管理员</option>
                    <option value="2">订单管理员</option>
                    <option value="3" selected>会员管理员</option>
                    <option value="4">系统管理员</option>
                </c:if>
                <c:if test="${manage.mstatus==4}">
                    <option value="1" >商品管理员</option>
                    <option value="2">订单管理员</option>
                    <option value="3">会员管理员</option>
                    <option value="4" selected>系统管理员</option>
                </c:if>
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