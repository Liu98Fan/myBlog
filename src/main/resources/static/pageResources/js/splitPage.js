/**
 * 分页功能实现
 * @Author：LF
 * @Date：2018-4-18
 */
//分页相关参数
var start = 0;//起始页
var limit = 10;//每页显示条数
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
    var s = "<div class=\"panel-heading\">\n" +
        "                        <h3 class=\"panel-title\">我的笔记</h3>\n" +
        "                        <div class=\"right\">\n" +
        "                            <button type=\"button\" class=\"btn-toggle-collapse\"><i class=\"lnr lnr-chevron-up\"></i></button>\n" +
        "                        </div>\n" +
        "                    </div>";
    $(data).each(function(index,object){

        s += "<div class=\"panel-body\">\n" +
            "                        <div class=\"col-sm-6\">\n" +
            "                            <h3 class=\"\"><a href=\"/note/getNote/"+ object.id+ "\">"+object.title +"</a></h3>\n" +
            "                            <p class=\"\">"+ object.newdate+"</p>\n" +
            "                        </div>\n" +
            "                        <div class=\"col-sm-6 text-right\">\n" +
            "                            <a href=\"/note/editor/"+object.id+"\" class=\"btn btn-success" +
            " \">编辑</a>\n" +
            "                            <a  href=\"/note/delete/"+object.id+"\" class=\"btn btn-danger" +
            " reset-panel\">删除</a>\n" +
            "                        </div>\n" +
            "                    </div>";

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
    if($("#cselect").val()!="-1"){
        $("#classifyName").attr("type","hidden");
        $("#classifyName").val($("#cselect").find("option:selected").text());
        $("#classifyName").removeAttr("required");
    }


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