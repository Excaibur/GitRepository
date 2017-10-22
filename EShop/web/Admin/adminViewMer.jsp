<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>
<body>

<form action="" enctype="multipart/form-data">
    <table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr height="40">
            <td colspan="2" class="itemTitle" align="center">
                查看详情
            </td>
        </tr>
        <tr height="24">
            <td width="160" align="right">商品类别：</td>
            <td><input type="text" name="category" styleClass="textBox" readonly="true" value="${proBean.typename}"/>
            </td>
        </tr>
        <tr height="24">
            <td valign="top" align="right">商品名称：</td>
            <td><input type="text" name="merName" size="41" styleClass="textBox" readonly="true"
                       value="${proBean.proname}"/></td>
        </tr>
        <tr height="24">
            <td width="160" align="right">商品型号：</td>
            <td><input type="text" name="merModel" size="41" styleClass="textBox" readonly="true"
                       value="${proBean.promodel}"/></td>
        </tr>
        <tr height="24">
            <td valign="top" align="right">商品图片：</td>

            <c:if test="${!empty proBean.propic}">
                <td><img id="picture" src="${proBean.propic}" height="80" width="80" border="1"/></td>
            </c:if>
            <c:if test="${empty proBean.propic}">
                <td><img id="" src="../images/default.jpg" height="80" width="80" border="1"/></td>
            </c:if>
        </tr>
        <tr height="24">
            <td width="160" align="right">市场价：</td>
            <td><input type="text" name="price" size="41" styleClass="textBox" readonly="true"
                       value="${proBean.price}"/></td>
        </tr>
        <tr height="24">
            <td width="160" align="right">特 价：</td>
            <td><input type="text" name="sprice" size="41" styleClass="textBox" readonly="true"
                       value="${proBean.sprice}"/></td>
        </tr>
        <tr height="24">
            <td valign="top" align="right">商品描述：</td>
            <td><textarea name="merDesc" cols="40" rows="10" styleClass="textBox"
                          readonly="true">${proBean.prodesc}</textarea></td>
        </tr>
        <tr height="24">
            <td width="160" align="right">生产厂家：</td>
            <td><input type="text" name="manufacturer" size="41" styleClass="textBox" readonly="true"
                       value="${proBean.profactory}"/></td>
        </tr>
        <tr height="24">
            <td valign="top" align="right">出厂日期：</td>
            <td><input type="text" name="leaveFactoryDate" size="41" readonly="true" styleClass="textBox"
                        value="${proBean.facdate}"/></td>
        </tr>
        <tr height="24">
            <td colspan="2" align="center">
                <a href="javascript:history.back()">
                    返回
                </a>
            </td>
        </tr>
    </table>
</form>

</body>
</html>