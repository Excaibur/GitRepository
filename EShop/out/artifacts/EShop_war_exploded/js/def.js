function selLike() {
    var name=$("#qKey").val();
    var category=$("#category").val();
    location.href="/def?action=like&qKey="+name+"&category="+category;
}

function changePage(curpage,count) {
    var name=$("#qKey").val();
    var category=$("#category").val();
    var page=1;
    if (curpage<=0){
        page=1;
    }else if (curpage>=count){
        page=count;
    }else {
        page=curpage;
    }
    location.href="/def?action=like&qKey="+name+"&category="+category+"&curpage="+page;
}

function buy(proid, mid) {
    if (mid!=""&&proid!=""){
        $.ajax({
            url:"/cart",
            type:"post",
            async:true,
            data:{"action":"add","proid":proid,"mid":mid},
            success:function (date) {
                alert(date);
            }
        });
    }else {
        location.href="sorry.jsp";
    }
}

function chkDel(cid) {
    if (confirm("是否要删掉该商品？")){
        $.ajax({
            url:"/cart",
            type:"get",
            async:true,
            data:{"action":"del","cid":cid},
            success:function (date) {
                alert(date);
                location.href="/chk?action=chk&name=cart";
            }
        });
    }
}

function changeNum(mid,proid) {
    var num=$("input[name=num]").val();
    if (num!=""){
        $.getJSON("/cart",{"action":"update","proid":proid,"mid":mid,"num":num},function (date) {
            if (date.sprice!=0.0){
                $("#price"+proid).text(date.sprice*date.precent);
            }else {
                $("#price"+proid).text(date.price*date.precent);
            }
            $("#money"+proid).text(date.money);
            $("#totalMoney").text(date.totalmoney);
        })
    }else {
        chkDel(mid,proid);
    }
}

function clearCart(mid) {
    if (confirm("是否要清空购物车？")){
        location.href="/cart?action=delAll&mid="+mid;
    }
}

function continueBuy() {
    location.href="index.htm";
}

function next(id) {
    if (id!=""){
        location.href="/def?action=memlv&lvid="+id;
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