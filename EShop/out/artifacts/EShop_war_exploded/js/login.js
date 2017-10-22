function login() {
    var name = $("input[name=loginName]").val();
    var pwd = $("input[name=loginPwd]").val();
    if (name != "" && pwd != "") {
        $("#login").submit();
    } else {
        alert("请输入用户名密码");
        return false;
    }
}

function logout() {
    // $.sessionStorage.removeItem('name');
    // $.session.remove('name');
    location.href = "/login?action=out";
}

function chkStatus(status) {
    if (status==0){
        location.href="/Admin/adminAddUser.jsp";
    }else {
        location.href="/Admin/sorry.jsp";
    }
}