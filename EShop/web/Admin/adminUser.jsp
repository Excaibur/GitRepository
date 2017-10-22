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
    <script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="../js/chkUser.js"></script>
    <script type="text/javascript" src="../js/login.js"></script>
</head>
<body>

<table border="0" align="center" cellpadding="0" cellspacing="0" style="background-color:lightgrey; border:0px;">
    <tr>
        <td height="40" class="itemTitle" align="center"></td>
    </tr>
    <tr>
        <td height="30" class="blueText" align="center">
            <a href="javascript:chkStatus('${status}')">
                <span class="blueText">新增管理员</span>
            </a>
        </td>
    </tr>
    <tr>
        <td height="30" align="center">
            <table>
                <tr>
                    <td>管理员真实姓名</td>
                    <td>帐 号</td>
                    <td>管理员类型</td>
                    <td> 管理员维护</td>
                </tr>
                <c:if test="${!empty list}">
                    <c:forEach items="${list}" var="list">
                        <tr>
                            <td>${list.mname}</td>
                            <td>${list.muser}</td>
                            <td><c:choose>
                                <c:when test="${list.mstatus==0}">
                                    超级管理员
                                </c:when>
                                <c:when test="${list.mstatus==1}">
                                    商品管理员
                                </c:when>
                                <c:when test="${list.mstatus==2}">
                                    订单管理员
                                </c:when>
                                <c:when test="${list.mstatus==3}">
                                    会员管理员
                                </c:when>
                                <c:when test="${list.mstatus==4}">
                                    系统管理员
                                </c:when>
                            </c:choose></td>
                            <td><a href="/manage?action=selId&id=${list.mid}">修改管理员 </a><a href="javascript:chkDel('${list.mid}')">删除管理员</a></td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty list}">
                    <tr><td>暂无数据</td></tr>
                </c:if>
            </table>
        </td>
    </tr>
</table>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/manage?action=list&curpage=1"><font size="1px">首页</font></a>&nbsp;&nbsp;
<a href="/manage?action=list&curpage=${page-1}"><font size="1px">上一页</font></a>&nbsp;&nbsp;
<a href="/manage?action=list&curpage=${page+1}"><font size="1px">下一页</font></a>&nbsp;&nbsp;
<a href="/manage?action=list&curpage=${endpage}"><font size="1px">末页</font></a>
</body>
</html>