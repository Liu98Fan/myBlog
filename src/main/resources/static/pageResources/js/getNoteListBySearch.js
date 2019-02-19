/**
 * 分页功能实现
 * @Author：LF
 * @Date：2018-4-18
 */
//分页相关参数
var limitBySearch = 8;//每页显示条数
var NumberBySearch = 0;
var startBySearch = 0;//起始页
var nowPageBySearch = 1;//当前页
var searchV = null;
function getInformationBySearch(search){
    searchV = search
//AJAX向后台请求数据
    $.ajax({
        type:'get',
        url:"/note/getNotePageBySearch",
        sync:false,
        data:{start:startBySearch,limit:limitBySearch,nowPage:nowPageBySearch,Number:NumberBySearch,"search":searchV},
        success:function(data){
            if(data.root.length>0){
                setHtml(data.root)
                startBySearch = data.currentResult;
                NumberBySearch = data.total;
                //进行分页初始化
                pageReadyBySearch(startBySearch,limitBySearch,nowPageBySearch,NumberBySearch);
            }else{
                toastr.success("并没有查询到相关笔记，请换一个关键词吧~")
            }


        }
    });
}

function setHtmlBySearch(data){
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

function pageReadyBySearch(){
//layui分页开启
    layui.use('laypage',function(){
        var laypage = layui.laypage;
        laypage.render({
            elem:'changePage'
            ,count:NumberBySearch//后台传入
            ,limit:limitBySearch
            ,curr:nowPageBySearch
            ,jump:function(obj, first){
                //obj包含了当前分页的所有参数，比如：
                // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                // console.log(obj.limit); //得到每页显示的条数
                nowPageBySearch = obj.curr;
                startBySearch = (obj.curr-1)*obj.limit;
                //首次不执行
                if(!first){
                    getInformationBySearch(searchV);
                }

            }
        });
    })
}
