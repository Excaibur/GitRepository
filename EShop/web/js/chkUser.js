function chkAddUser() {
    $("#regform").validate({
        rules: {
            adminName: {
                required: true
            },
            loginName: {
                required: true
            },
            loginPwd: {
                required: true,
                rangelength: [8, 12]
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
            }
        }
    });
    if ($("#regform").valid()) {
        $("#regform").submit();
    }
}

function chkLoginName() {
    if ($("input[name=loginName]").val() != null && $("input[name=loginName]").val() != "") {
        $.post("/chk",{"action":"chkUserName","loginName":$("input[name=loginName]").val()}, function (date) {
            $("#loginName").html(date);
        });
    }
}

function chkDel(id) {
    if (confirm("是否要删掉此条数据？")){
        location.href="/manage?action=del&id="+id;
    }
}