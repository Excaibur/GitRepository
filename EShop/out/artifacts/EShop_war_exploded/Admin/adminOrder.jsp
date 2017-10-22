<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>电子商城系统--后台管理</title>
    <link rel="stylesheet" type="text/css" href="../CSS/stylesheet.css">
    <link rel="stylesheet" type="text/css" href="../CSS/displaytag.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style type="text/css">
        <!--
        body {
            background-color: lightgrey;
        }

        -->
    </style>
    <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/js/order.js"></script>
</head>
<body>

<table border="0" align="center" cellpadding="0" cellspacing="0" style="background-color:lightgrey; border:0px;">
    <tr>
        <td height="40" class="itemTitle" align="center">订单管理</td>
    </tr>
    <tr>
        <td height="30" align="center">
            <table>
                <tr>
                    <td>订单编号</td>
                    <td>金额</td>
                    <td>下单日期</td>
                    <td>订单状态</td>
                    <td> 编 辑</td>
                </tr>
                <c:if test="${!empty list}">
                    <c:forEach items="${list}" var="list">
                        <tr>
                            <td>${list.orderid}</td>
                            <td class="redText">￥${list.ordermoney}</td>
                            <td>${list.orderdate}</td>
                            <td>
                                <c:if test="${list.orderstatus==0}">已下单，未受理</c:if>
                                <c:if test="${list.orderstatus==1}">已受理，处理中</c:if>
                                <c:if test="${list.orderstatus==2}">处理完毕</c:if>
                                <c:if test="${list.orderstatus==3}">结单</c:if>
                            </td>
                            <td><a href="/order?action=selId&oid=${list.orderid}">查看订单</a>
                                <c:if test="${list.orderstatus<3}">
                                    <a href="/order?action=update&oid=${list.orderid}&curpage=${page}">受理该订单</a>
                                </c:if>
                                <a href="javascript:delOrder('${list.orderid}','${page}')">删除订单</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
            <!--  已下单，未受理 已受理，处理中 处理完毕 查看订单 受理该订单 结单 删除订单-->
        </td>
    </tr>
</table>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/order?action=list&curpage=1"><font size="1px">首页</font></a>&nbsp;&nbsp;
<a href="/order?action=list&curpage=${page-1}"><font size="1px">上一页</font></a>&nbsp;&nbsp;
<a href="/order?action=list&curpage=${page+1}"><font size="1px">下一页</font></a>&nbsp;&nbsp;
<a href="/order?action=list&curpage=${endpage}"><font size="1px">末页</font></a>
</body>
</html>