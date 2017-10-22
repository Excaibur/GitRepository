<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html> 
	<head>
		<title>电子商城系统--后台管理</title>
		<link rel="stylesheet" type="text/css" href="../CSS/stylesheet.css">
		<link rel="stylesheet" type="text/css" href="../CSS/displaytag.css" />		
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<style type="text/css">
		<!--
		body {
			background-color: lightgrey;
		}
		-->
	</style>
        <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
    </head>
  <body>
	<table border="0" align="center" cellpadding="0" cellspacing="0" style="background-color:lightgrey; border:0px;">
	  <tr>
		<td height="40" class="itemTitle" align="center">查看订单</td>
	  </tr>
	  <tr>
	    <td height="30" align="center">
		<table width="94%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
         
            <tr bgcolor="#F7F3F7"><td height="10" colspan="2"></td></tr>
            <tr bgcolor="#F7F3F7" class="text">
              <td width="220" height="26" align="right">订单编号
                ：</td>
              <td>&nbsp;${order.orderid}</td>
            </tr>
            <tr bgcolor="#F7F3F7" class="text">
              <td width="220" height="26" align="right">金额 
                ：</td>
              <td>&nbsp;￥${order.ordermoney}</td>
            </tr>
           
            <tr bgcolor="#F7F3F7" class="text">
              <td width="220" height="26" align="right">下单日期
                ：</td>
              <td>&nbsp;${order.orderdate}</td>
            </tr>
            <tr bgcolor="#F7F3F7" class="text">
              <td width="220" height="26" align="right">会员级别
                ：</td>
              <td>&nbsp;${order.lvname}</td>
            </tr>
            <tr bgcolor="#F7F3F7" class="text">
              <td width="220" height="26" align="right">会员优惠
                ：</td>
              <td>&nbsp;${order.precent*10}折</td>
            </tr>
            <tr bgcolor="#F7F3F7" class="text">
              <td width="220" height="26" align="right">收货人姓名
                ：</td>
              <td>&nbsp;${order.ordername}</td>
            </tr>
            <tr bgcolor="#F7F3F7" class="text">
              <td width="220" height="26" align="right">收货人联系电话
                ：</td>
              <td>&nbsp;${order.orderphone}</td>
            </tr>
            <tr bgcolor="#F7F3F7" class="text">
              <td width="220" height="26" align="right">收货人邮编
                ：</td>
              <td>&nbsp;${order.orderzip}</td>
            </tr>
            <tr bgcolor="#F7F3F7" class="text">
              <td width="220" height="26" align="right">收货人详细地址
                ：</td>
              <td>&nbsp;${order.orderaddress}</td>
            </tr>
            <tr bgcolor="#F7F3F7"><td height="10" colspan="2"></td></tr>
            <tr bgcolor="#F7F3F7" class="text">
              <td height="40" colspan="2" align="center" bgcolor="#FFFFFF" valign="bottom"></td>
            </tr>
          
        </table>
		  <div align="center" class="itemTitle">订单购物明细表</div>
		  <table cellspacing="1" cellpadding="0" width="100%" border="0" bgcolor="#F7F3F7">
			  <tr height="30" bgcolor="#F7F3F7">
				<td class="blackTitle" align="center">商品名称</td>
				<td class="blackTitle" align="center">市场价</td>
				<td class="blackTitle" align="center">会员价</td>
				<td class="blackTitle" align="center">数量</td>
				<td class="blackTitle" align="center">金额</td>
			  </tr>
              <c:if test="${!empty order.infoList}">
                  <c:forEach var="info" items="${order.infoList}">
                      <tr class="text" align="center" bgcolor="#FFFFFF">
                          <td>&nbsp;${info.proname}</td>
                          <td>￥${info.price}</td>
                          <td>￥${info.money/info.pronum}</td>
                          <td>${info.pronum}</td>
                          <td>￥${info.money}</td>
                      </tr>
                  </c:forEach>
              </c:if>
			  <tr height="10" bgcolor="#F7F3F7">
				<td colspan="5"></td>
			  </tr>
		  </table>		
		</td>
      </tr>
  </table>
  <center>
	<a href="javascript:history.back();">
		<span class="redText">返回</span>
	</a>
  </center>
  </body>
</html>