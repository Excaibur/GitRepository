<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html> 
	<head>
	<title>电子商城系统--后台管理</title>
	<link rel="stylesheet" type="text/css" href="../CSS/stylesheet.css">			
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<style type="text/css">
		<!--
		body {
			background-color: lightgrey;
		}
		-->
	</style>
        <script type="text/javascript" src="../js/memreg.js"></script>
        <script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
	</head>
  <body>
	<table border="0" align="center" cellpadding="0" cellspacing="0" style="background-color:lightgrey; border:0px;">
	  <tr>
		<td height="40" class="itemTitle" align="center">会员详情</td>
	  </tr>
	  <tr>
	    <td height="30" align="center">
		<table width="94%" border="0" cellpadding="0" cellspacing="0">
         
            <tr><td height="10" colspan="2"></td></tr>
            <tr>
              <td width="160" height="30" align="right">会员级别
                ：</td>
                <td>
                    <select id="memberlevel">
                        <c:forEach items="${lvlist}" var="lv">
                            <c:choose>
                                <c:when test="${mBean.lvname==lv.lvname}">
                                    <option value="${lv.lvid}" selected>${lv.lvname}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${lv.lvid}">${lv.lvname}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select><br>
			  	<input type="button" value='调整会员级别' onclick="updateLv('${mBean.memid}')"/>
			  </td>
            </tr>
            <tr>
              <td width="160" height="30" align="right">登录帐号
                ：</td>
              <td>${mBean.memlogin}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right">真实姓名
                ：</td>
              <td>${mBean.memname}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right">联系电话
                ：</td>
              <td>${mBean.memphone}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right">联系地址
                ：</td>
              <td>${mBean.memaddress}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right">邮政编码
                ：</td>
              <td>${mBean.memzip}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right">电子邮箱
                ：</td>
              <td>${mBean.email}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right">注册日期
                ：</td>
              <td>${mBean.regdate}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right">登录次数
                ：</td>
              <td>${mBean.loginnum}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right">最近登录日期
                ：</td>
              <td>${mBean.logindate}</td>
            </tr>
            <tr><td height="10" colspan="2"></td></tr>
        </table>
		</td>
      </tr>
  </table>
  <center>
	<a href="/mem?action=list">
		<span>返回</span>
	</a>
  </center>

</body>
</html>