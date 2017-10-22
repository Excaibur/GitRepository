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
    <script type="text/javascript" src="../js/memreg.js"></script>
</head>
<body>

<table border="0" align="center" cellpadding="0" cellspacing="0" style="background-color:lightgrey; border:0px;">
    <tr>
        <td height="40" class="itemTitle" align="center">会员管理</td>
    </tr>
    <tr>
        <td height="30" class="blueText" align="center">
            <a href="/alword?action=list">
                <span class="blueText">留言管理</span>
            </a>
        </td>
    </tr>
    <tr>
        <td height="30" align="center">
            <table>
                <tr>
                    <td>登录帐号</td>
                    <td>真实姓名</td>
                    <td>会员级别</td>
                    <td>联系电话</td>
                    <td>注册日期</td>
                    <td>编 辑</td>
                </tr>
                <c:choose>
                    <c:when test="${!empty list}">
                        <c:forEach var="list" items="${list}">
                            <tr>
                                <td>${list.memlogin}</td>
                                <td>${list.memname}</td>
                                <td>${list.lvname}</td>
                                <td>${list.memphone}</td>
                                <td>${list.regdate}</td>
                                <td>
                                    <a href="/mem?action=selId&id=${list.memid}">会员详情</a>
                                    <a href="javascript:chkDel('${list.memid}')">删除会员</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>暂无数据</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>

        </td>
    </tr>
    <tr>
        <td height="30" align="center" class="redText"><html:errors property="delMemberStatus"/></td>
    </tr>
</table>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/mem?action=list&curpage=1"><font size="1px">首页</font></a>&nbsp;&nbsp;
<a href="/mem?action=list&curpage=${page-1}"><font size="1px">上一页</font></a>&nbsp;&nbsp;
<a href="/mem?action=list&curpage=${page+1}"><font size="1px">下一页</font></a>&nbsp;&nbsp;
<a href="/mem?action=list&curpage=${endpage}"><font size="1px">末页</font></a>
</body>
</html>