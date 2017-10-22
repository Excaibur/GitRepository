function chkReg() {
    $("#regform").validate({
        rules: {
            memberName: {
                required: true
            },
            loginName: {
                required: true
            },
            loginPwd: {
                required: true,
                rangelength: [8, 12]
            },
            reLoginPwd: {
                required: true,
                equalTo:"#loginPwd"
            },
            phone: {
                required: true,
                rangelength: [11, 11]
            },
            zip: {
                isZipCode: true
            },
            email: {
                email: true
            }
        },
        messages: {
            memberName: {
                required: "请输入真实姓名"
            },
            loginName: {
                required: "请输入用户名"
            },
            loginPwd: {
                required: "请输入密码",
                rangelength: "请输入8-12位密码"
            },
            reLoginPwd: {
                required: "请确认密码",
                equalTo: "两次密码不一致"
            },
            phone: {
                required: "请输入联系电话",
                rangelength: "请输入正确的联系电话"
            },
            zip: {
                isZipCode: "请输入正确的邮编"
            },
            email: {
                email: "请输入正确的邮箱"
            }
        }
    });
    if ($("#regform").valid()) {
        $("#regform").submit();
    }
}

function chkLoginName() {
    if ($("input[name=loginName]").val() != null && $("input[name=loginName]").val() != "") {
        $.post("/chk",{"action":"chkMemName","loginName":$("input[name=loginName]").val()}, function (date) {
                $("#loginName").html(date);
            });
    }
}

function updateLv(id) {
    $.post("/mem",{"action":"updateLv","id":id,"memberlevel":$("select option:selected").val()}, function (date) {
        alert(date);
    });
}

function chkDel(id) {
    if (confirm("是否要删掉此条数据？")){
        location.href="/mem?action=del&id="+id;
    }
}