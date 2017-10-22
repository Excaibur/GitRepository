function chkAddCate() {
    $("#cateform").validate({
        rules: {
            cateName: {
                required: true
            },
            cateDesc: {
                required: true
            }
        },
        messages: {
            cateName: {
                required: "请输入真实姓名"
            },
            cateDesc: {
                required: "请输入用户名"
            }
        }
    });
    if ($("#cateform").valid()) {
        $("#cateform").submit();
    }
}

function chkCateName() {
    if ($("input[name=cateName]").val() != null && $("input[name=cateName]").val() != "") {
        $.post("/chk",{"action":"chkProType","cateName":$("input[name=cateName]").val()}, function (date) {
            $("#cateName").html(date);
        });
    }
}

function chkDel(id) {
    if (confirm("删除该类型将同时删除该类型的所有商品")){
        location.href="/ptype?action=del&id="+id;
    }
}