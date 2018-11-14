/**
 * 分页功能实现
 * @Author：LF
 * @Date：2018-4-18
 */
//分页相关参数
var start = 0;//起始页
var limit = 8;//每页显示条数
var nowPage = 1;//当前页
var Number = 0;//数据量

function getInformation(){
//AJAX向后台请求数据
    $.ajax({
        type:'get',
        url:"/note/getNotePage",
        sync:false,
        data:{start:start,limit:limit,nowPage:nowPage,Number:Number},
        success:function(data){
            setHtml(data.root)
            start = data.currentResult;
            Number = data.total;
            //进行分页初始化
            pageReady();
        }

    });
}

function setHtml(data){
    var s = "";
    $(data).each(function(index,object){

        s += "<div th:class=\"row\">\n" +
            "                                <div\n" +
            "                                        class=\"col-sm-1 text-center\"\n" +
            "                                        ><span class=\"glyphicon glyphicon-pencil\"\n" +
            "                                                                           style=\"font-size:35px;\"\n" +
            "                                ></span></div>\n" +
            "                                <div class=\"col-sm-9\">\n" +
            "                                    <h3><a href=\"/note/getNote/"+object.id+"\">"+object.title+"&nbsp;</a><span class=\"label label-default\">"+object.noteclassify.classifyname+"</span></h3>\n" +
            "                                </div>\n" +
            "                                <div class=\"col-sm-2\" >\n" +
            "                                    <h4 class=\"co-grey\">"+object.date+"</h4>\n" +
            "                                </div>\n" +
            "                                <hr class=\"layui-bg-gray\">\n" +
            "                            </div>";

    });
    if(data.length>0){
        $("#xianshi").html(s);
    }else{
        $("#xianshi").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
    }
    //console.log(s);
}


function pageReady(){
//layui分页开启
    layui.use('laypage',function(){
        var laypage = layui.laypage;
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
    })
}

$(document).ready(function(){
    //ajax先拿到后台数据
    getInformation();

});