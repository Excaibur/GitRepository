function chkAddOrder() {
    $("#form1").validate({
        rules: {
            memName: {
                required: true
            },
            phone: {
                required: true,
                rangelength: [11, 11]
            },
            zip:{
                isZipCode: true
            },
            address:{
                required: true
            }
        },
        messages: {
            memName: {
                required: "请输入收件人姓名"
            },
            phone: {
                required: "请输入联系电话",
                rangelength: "请输入正确的联系电话"
            },
            zip:{
                isZipCode: "请输入正确的邮编"
            },
            address:{
                required: "请输入收货地址"
            }
        }
    });
    if ($("#form1").valid()) {
        $("#form1").submit();
    }
}

function back() {
    history.back();
}

function chkDel(oid) {
    if (confirm("是否要删掉此条数据？")){
        location.href="/myorder?action=del&oid="+oid;
    }
}

function delOrder(oid,page) {
    if (confirm("是否要删掉此订单？")){
        location.href="/order?action=del&oid="+oid+"&curpage="+page;
    }
}

function updateOrder(oid) {

}