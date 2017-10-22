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
    <script type="text/javascript" src="../js/chkCate.js"></script>
    <script type="text/javascript" src="../js/login.js"></script>
</head>
<body>

<table border="0" align="center" cellpadding="0" cellspacing="0" style="background-color:lightgrey; border:0px;">
    <tr>
        <td height="40" class="itemTitle" align="center">商品分类管理</td>
    </tr>
    <tr>
        <td height="30" class="blueText" align="center">
            <a href="/Admin/adminAddCate.jsp">
                <span class="blueText">新增商品分类 </span>
            </a>
        </td>
    </tr>
    <tr>
        <td height="30" align="center">
            <table>
                <tr>
                    <td>商品分类名称</td>
                    <td>商品分类描述</td>
                    <td>商品分类维护</td>
                </tr>
                <c:choose>
                    <c:when test="${!empty list}">
                        <c:forEach items="${list}" var="list">
                            <tr>
                                <td>${list.typename}</td>
                                <td>${list.typedesc}</td>
                                <td><a href="/ptype?action=selId&id=${list.typeid}">修改商品分类 </a>
                                    <a href="javascript:chkDel('${list.typeid}')">删除商品分类</a>
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
</table>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/ptype?action=list&curpage=1"><font size="1px">首页</font></a>&nbsp;&nbsp;
<a href="/ptype?action=list&curpage=${page-1}"><font size="1px">上一页</font></a>&nbsp;&nbsp;
<a href="/ptype?action=list&curpage=${page+1}"><font size="1px">下一页</font></a>&nbsp;&nbsp;
<a href="/ptype?action=list&curpage=${endpage}"><font size="1px">末页</font></a>
</body>
</html>