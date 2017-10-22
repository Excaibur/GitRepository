function checkForm() {
    $("#form1").validate({
        rules: {
            wordTitle: {
                required: true
            },
            content: {
                required: true
            }
        },
        messages: {
            memberName: {
                required: "标题不能为空"
            },
            loginName: {
                required: "请输入留言内容"
            }
        }
    });
    if ($("#form1").valid()) {
        $("#form1").submit();
    }
}

function goPage() {
    var page=$("#willGoPage").val();
    var regex=/^[0-9]+.?[0-9]*$/;
    if(regex.test(page)){
        location.href="/def?action=more&pro=pro&curpage="+page;
    }else {
        return false;
    }
}

function chkAnswer() {
    $("#answerForm").validate({
        rules: {
            answerContent: {
                required: true
            }
        },
        messages: {
            answerContent: {
                required: "请输入留言内容"
            }
        }
    });
    if ($("#answerForm").valid()) {
        $("#answerForm").submit();
    }
}

function delWord(id,page) {
    if (confirm("是否删除此条留言？")){
        location.href="/alword?action=del&id="+id+"&curpage="+page;
    }else {
        return false;
    }
}