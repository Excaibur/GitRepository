function chkAddPro() {
    $("#proform").validate({
        rules: {
            merName: {
                required: true
            },
            merModel: {
                required: true
            },
            price: {
                required: true,
                number:true
            },
            sprice: {
                number:true
            },
            manufacturer: {
                required: true
            },
            leaveFactoryDate: {
                required: true
            },
            category:{
                required: true
            }
        },
        messages: {
            merName: {
                required: "请填写名称"
            },
            merModel: {
                required: "请填写型号"
            },
            price: {
                required: "请填写价格",
                number:"请填写正确价格"
            },
            sprice: {
                number:"请填写正确价格"
            },
            manufacturer: {
                required: "请填写出版商"
            },
            leaveFactoryDate: {
                required: "请填写出版时间"
            },
            category:{
                required: "请选择类型"
            }
        }
    });
    if ($("#proform").valid()) {
        $("#proform").submit();
    }
}

function changePrice() {
    var flag=$("input[name=special]:checked").val();
    if (flag==0){
        $("input[name=sprice]").prop("readonly","readonly");
    }else if (flag==1){
        $("input[name=sprice]").prop("readonly",false);
    }
}

function chkDel(id) {
    if (confirm("是否要删掉此条数据？")){
        location.href="/pro?action=del&id="+id;
    }
}