<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/js/jquery-validate.js"></script>
    <script type="text/javascript" src="/js/word.js"></script>
</head>
<body>
<table border="0" align="center" cellpadding="0" cellspacing="0" style="background-color:lightgrey; border:0px;">
    <tr>
        <td height="40" class="itemTitle" align="center">会员详情</td>
    </tr>
    <tr>
        <td height="30" align="center">
            <table width="94%" border="0" cellpadding="0" cellspacing="0">

                <tr>
                    <td height="10" colspan="2"></td>
                </tr>
                <tr>
                    <td width="160" height="30" align="right">留言标题
                        ：
                    </td>
                    <td>&nbsp;${lwBean.wtitle}</td>
                </tr>
                <tr>
                    <td width="160" height="30" align="right">顾客姓名
                        ：
                    </td>
                    <td>&nbsp;${lwBean.memlogin}</td>
                </tr>
                <tr>
                    <td width="160" height="30" align="right">管理员回复
                        ：
                    </td>
                    <td>&nbsp;${lwBean.wmcontent}</td>
                </tr>
                <tr>
                    <td width="160" height="30" align="right">留言内容
                        ：
                    </td>
                    <td>&nbsp;${lwBean.wcontent}</td>
                </tr>
                <c:if test="${lwBean.iscontent==0}">
                    <tr>
                        <td width="160" height="30" align="right">管理员回复
                            ：
                        </td>
                        <td>&nbsp;
                            <form id="answerForm" action="/alword?action=upload&id=${lwBean.wid}" method="post"
                                  style="margin:0px;">
                                <textarea class="textBox" id="answerContent" name="answerContent" rows="6"
                                          cols="60"></textarea>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td height="40" colspan="2" align="center"><input type="button" value='回复该留言'
                                                                          onClick="chkAnswer()"/></td>
                    </tr>
                    <tr>
                        <td height="10" colspan="2"></td>
                    </tr>
                </c:if>
            </table>
        </td>
    </tr>
</table>
<center>
    <a href="/alword?action=list">
        <span>返回</span>
    </a>
</center>

</body>
</html>