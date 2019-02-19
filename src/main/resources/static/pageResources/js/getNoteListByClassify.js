/**
 * 分页功能实现
 * @Author：LF
 * @Date：2018-4-18
 */
//分页相关参数
var limitByClassify = 8;//每页显示条数
var NumberByClassify = 0;
var startByClassify = 0;//起始页
var nowPageByClassify = 1;//当前页
var classify = null;
function getInformationByClassify(classifyId){
    classify = classifyId;
//AJAX向后台请求数据
    $.ajax({
        type:'get',
        url:"/note/getNotePageByClassifyId",
        sync:false,
        data:{start:startByClassify,limit:limitByClassify,nowPage:nowPageByClassify,Number:NumberByClassify,"classifyId":classify},
        success:function(data){
            setHtmlByClassify(data.root)
            startByClassify = data.currentResult;
            NumberByClassify = data.total;
            //进行分页初始化
            pageReadyByClassify(startByClassify,limitByClassify,nowPageByClassify,NumberByClassify);
        }

    });
}
function setHtmlByClassify(data){
    var s = "";
    $(data).each(function(index,object){

        s += "<div th:class=\"row\">\n" +
            "                                <div\n" +
            "                                        class=\"col-sm-1 text-center\"\n" +
            "                                        ><span class=\"glyphicon glyphicon-pencil\"\n" +
            "                                                                           style=\"font-size:35px;\"\n" +
            "                                ></span></div>\n" +
            "                                <div class=\"col-sm-9\">\n" +
            "                                    <h3><a href=\"/note/getNote/"+object.id+"\">"+object.title+"&nbsp;</a><span class=\"label label-default\">"+object.noteclassify.classifyname+"</span>";
        if(object.resourceCount>0){
            s+="<span style='margin-left: 5px;' class='label label-success'>相关资源数:"+object.resourceCount+"</span>"
        }

        s+="</h3>\n" +
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
}

function pageReadyByClassify(){
//layui分页开启
    layui.use('laypage',function(){
        var laypage = layui.laypage;
        laypage.render({
            elem:'changePage'
            ,count:NumberByClassify//后台传入
            ,limit:limitByClassify
            ,curr:nowPageByClassify
            ,jump:function(obj, first){
                //obj包含了当前分页的所有参数，比如：
                // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                // console.log(obj.limit); //得到每页显示的条数
                nowPageByClassify = obj.curr;
                startByClassify = (obj.curr-1)*obj.limit;
                //首次不执行
                if(!first){
                    getInformationByClassify(classify);
                }

            }
        });
    })
}
