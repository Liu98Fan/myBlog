/**
 * 分页功能实现
 * @Author：LF
 * @Date：2018-4-18
 */
//分页相关参数
var start = 0;//起始页
var limit = 15;//每页显示条数
var nowPage = 1;//当前页
var Number = 0;//数据量

function getInformation(){
//AJAX向后台请求数据
    $.ajax({
        type:'get',
        url:"/manager/getUserForPage",
        sync:false,
        data:{start:start,limit:limit,nowPage:nowPage,Number:Number},
        success:function(data){
            setHtml(data.root)
            start = data.currentResult;
            Number = data.total;
            //进行分页初始化
            pageReady();
        },
        error:function () {
            alert("网络出错，请稍后重试。")
        }

    });
}

function setHtml(data){
    var s = "<div class=\"table-responsive\">\n" +
        "                        <table class=\"table table-bordered table-hover table-striped\">\n" +
        "                            <thead>\n" +
        "                            <tr>\n" +
        "                                <th>序号</th>\n" +
        "                                <th>用户名</th>\n" +
        "                                <th>注册时间</th>\n" +
        "                                <th>操作</th>\n" +
        "                            </tr>\n" +
        "                            </thead>\n" +
        "                            <tbody>";
    $(data).each(function(index,obj){
        s += "<tr>\n" +
            "                                <td>"+index+"</td>\n" +
            "                                <td>"+obj.username+"</td>\n" +
            "                                <td>"+obj.newdate+"</td>\n" +
            "                                <td><input type='hidden' id='id' value='"+obj.id+"'><button  id=\"deleteButton\" class=\"btn btn-danger\">删除</button> <button id=\"modifyPassword\"class=\"btn btn-danger\">修改密码</button><button data-toggle='modal' data-target='#myModal' id=\"displayRoleButton\"class=\"btn btn-danger\">查看角色信息</button></td>\n" +
            "                            </tr>";

    });
    s +="</tbody>\n" +
        "\n" +
        "\n" +
        "                        </table>\n" +
        "                    </div>"
    if(data.length>0){
        $("#xianshi").html(s);
    }else{
        $("#xianshi").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
    }
    //console.log(s);
}


function pageReady(){
//layui分页开启
    layui.use(['laypage','layer'],function(){
        var laypage = layui.laypage,
            layer = layui.layer;

        laypage.render({
            elem:'changePage'
            ,count:Number//后台传入
            ,limit:limit
            ,curr:nowPage
            ,jump:function(obj, first){
                //obj包含了当前分页的所有参数，比如：
                // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                // console.log(obj.limit); //得到每页显示的条数
                nowPage = obj.curr;
                start = (obj.curr-1)*obj.limit;
                //首次不执行
                if(!first){
                    getInformation();
                }

            }
        });
        var active = {
            offset:function (othis) {
                var type = othis.data('type')
                    ,text = "您确定要删除该用户么？";

                layer.open({
                    type: 1
                    ,offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                    ,id: 'layerDemo'+type //防止重复弹出
                    ,content: '<div style="padding: 20px 100px;color:red;">'+ text +'</div>'
                    ,btn: '关闭全部'
                    ,btnAlign: 'c' //按钮居中
                    ,shade: 0 //不显示遮罩
                    ,yes: function(){
                        layer.closeAll();
                    }
                });
            }


        }
    })
}

$(document).ready(function(){
    //ajax先拿到后台数据
    getInformation();

});