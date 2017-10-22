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
    <script type="text/javascript" src="../js/chkPro.js"></script>
</head>
<body>

<table border="0" align="center" cellpadding="0" cellspacing="0" style="background-color:lightgrey; border:0px;">
    <tr>
        <td height="40" class="itemTitle" align="center">商品管理</td>
    </tr>
    <tr>
        <td height="30" class="blueText" align="center">
            <a href="/pro?action=query">
                <span class="blueText">新增商品</span>
            </a>
        </td>
    </tr>
    <tr>
        <td height="30" align="center">
            <table>
                <tr>
                    <td>商品名称</td>
                    <td>商品型号</td>
                    <td>商品图片</td>
                    <td>市场价</td>
                    <td>商品维护</td>
                </tr>
                <c:choose>
                    <c:when test="${!empty list}">
                        <c:forEach items="${list}" var="list">
                            <tr>
                                <td>${list.proname}</td>
                                <td>${list.promodel}</td>
                                <td><a href="${list.propic}">查看图片</a></td>
                                <td>${list.price}</td>
                                <td><a href="/pro?action=selId&id=${list.proid}&fg=view">查看详情 </a>
                                    <a href="/pro?action=selId&id=${list.proid}&fg=update">修改商品</a>
                                    <a href="javascript:chkDel('${list.proid}')">删除商品 </a>
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
        <td height="30" align="center" class="redText"><html:errors property="delMerStatus"/></td>
    </tr>
</table>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/pro?action=list&curpage=1"><font size="1px">首页</font></a>&nbsp;&nbsp;
<a href="/pro?action=list&curpage=${page-1}"><font size="1px">上一页</font></a>&nbsp;&nbsp;
<a href="/pro?action=list&curpage=${page+1}"><font size="1px">下一页</font></a>&nbsp;&nbsp;
<a href="/pro?action=list&curpage=${endpage}"><font size="1px">末页</font></a>
<div id="tip" style="position:absolute;display:none;border:0px;width:80px; height:80px;">
    <TABLE id="tipTable" border="0" bgcolor="#ffffee">
        <TR align="center">
            <TD><img id="photo" src="" height="80" width="80"></TD>
        </TR>
    </TABLE>
</div>
<script language="javascript">
    function over(picPath) {
        if (picPath == "") picPath = "/images/default.jpg";
        x = event.clientX;
        y = event.clientY;
        document.all.tip.style.display = "block";
        document.all.tip.style.top = y;
        document.all.tip.style.left = x + 10;
        document.all.photo.src = ".." + picPath;
    }

    function out() {
        document.all.tip.style.display = "none";
    }
</script>
</body>
</html>