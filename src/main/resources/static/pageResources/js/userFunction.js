/**
 * 主要是用户验证的一些ajax方法
 **/

$(".username").on("blur",function () {

    if(/^[\u4e00-\u9fa5]+$/i.test($(".username").val())){
        toastr.error("用户名非法！不可包含中文字符");
        $("#submitRegister").attr("disabled","disabled");
        return ;
    }
    var mark = checkUsername($(".username").val());
    if(!mark){
        $("#submitRegister").attr("disabled","disabled");
        //toastr.error("用户名已经存在");
    }else{
        $("#submitRegister").removeAttr("disabled");
    }
})

function checkUsername( username){
    var username = username;
    var mark = true;
    $.ajax({
        url: "/user/checkUsername",
        dataType:"json",
        data:{"username":username},
        async:false,
        success:function (data) {
            //获取个人介绍的信息 json解析
            var success = data["success"];
            var message = data["message"];
            var code = data["code"];

            if(code==200){
                toastr.success("用户名可以使用！");
                //alert("true");
                mark= true;
            }else{
                // $("#usernameSpan").removeAttr("style","display");
                // $("#usernameSpan").attr("style","color:red");
                //alert("false");
                toastr.error("用户名已经存在！");
                mark=false;
            }


        },
        error:function(){
            toastr.error("网络错误，请稍后重试");
            mark = false;
        }
    })
    return mark;
}


