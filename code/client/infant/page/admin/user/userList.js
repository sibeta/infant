layui.config({
	base : "../../../js/"
}).use(['form','layer','jquery','laypage','config'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery,
        config = layui.config;

	var userData = '';
	getPageData();

	//查询
	$(".search_btn").click(function(){
		var username = $(".search_input").val();
		if(username != ''){
			getPageData(username, 1);
		}else{
			getPageData();
		}
	})

	//添加r
	$(".userAdd_btn").click(function(){
        if(typeof window.user !== 'undefined') {
            delete window.user;
        }
        openUserAddView("添加用户信息");
	})

	//操作
	$("body").on("click",".user_edit",function(){
        var userId = $(this).attr("data-id");
        var userList = userData.content;
        for(var i = 0; i < userList.length; i++){
            if(userList[i].userId == userId){
                window.user = userList[i];
                break;
            }
        }

        openUserAddView("编辑用户信息");
	})

	$("body").on("click",".user_del",function(){  //删除
        var _this = $(this);
        layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
            var userId = _this.attr("data-id");
            var data = {};
            data.userId = userId;

            $.ajax({
                url : config.serverBaseURL + "user/delete",
                type : "post",
                data : data,
                dataType : "json",
                success : function(result){
                    top.layer.close(index);
                    if(result.code === 0) {
                        getPageData();
                        layer.close(index);
                    }
                }
            })
        });
	})

    function renderUserList(){
        var dataHtml = '';
        var currData = userData.content;
        if(currData.length !== 0){
            for(var i=0;i<currData.length;i++){
                dataHtml += '<tr>'
                    +'<td align="left">'+currData[i].username+'</td>'
                    +'<td>'+currData[i].useralias+'</td>'
                    +'<td>'+currData[i].role+'</td>'
                    +'<td>'
                    +  '<a class="layui-btn layui-btn-mini user_edit" data-id="'+currData[i].userId+'"><i class="iconfont icon-edit"></i> 编辑</a>'
                    +  '<a class="layui-btn layui-btn-danger layui-btn-mini user_del" data-id="'+currData[i].userId+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
                    +'</td>'
                    +'</tr>';
            }
        }else{
            dataHtml = '<tr><td colspan="7">暂无数据</td></tr>';
        }

        $(".user_content").html(dataHtml);
    }

    function getPageData(username, page, size) {
        var data = {};
        data.username = username;
        data.page = page;
        data.size = size;

        var index = top.layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : config.serverBaseURL + "user/list",
            type : "post",
            data : data,
            dataType : "json",
            success : function(result){
                top.layer.close(index);
                if(result.code === 0) {
                    userData = result.data;
                    renderUserList();
                }
            },
            error : function(e) {
                top.layer.msg("查询失败");
                top.layer.close(index);
            }
        })
    }

    function openUserAddView(title) {
        var index = layui.layer.open({
            title : title,
            type : 2,
            content : "userAdd.html",
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        $(window).resize(function(){
            layui.layer.full(index);
        })
        layui.layer.full(index);
    }
})
