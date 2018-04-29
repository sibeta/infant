layui.config({
	base : "../../../js/"
}).use(['layer','jquery','config'],function(){
	var layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery,
		config = layui.config;

	//加载页面数据
	var categoryData = '';
	getPageData();

	//添加品类信息
	$(".categoryAdd_btn").click(function(){
        if(typeof window.category !== 'undefined') {
        	delete window.category;
		}
        openCategoryAddView("添加品类信息");
	})
 
	//操作
	$("body").on("click",".category_edit",function(){
        var categoryId = $(this).attr("data-id");
        var categoryList = categoryData.content;
        for(var i = 0; i < categoryList.length; i++){
            if(categoryList[i].categoryId == categoryId){
                window.category = categoryList[i];
                break;
            }
        }

        openCategoryAddView("编辑品类信息");
	});

	$("body").on("click",".category_del",function(){  //删除
		var _this = $(this);
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
			debugger;
			var categoryId = _this.attr("data-id");
			var data = {};
			data.categoryId = categoryId;

            $.ajax({
                url : config.serverBaseURL + "category/delete",
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

	function renderCategoryList(){
        var dataHtml = '';
        var currData = categoryData.content;
        if(currData.length !== 0){
            for(var i = 0; i < currData.length; i++){
                dataHtml += '<tr>'
                    +'<td align="left">'+currData[i].categoryId+'</td>'
                    +'<td>'+currData[i].categoryName+'</td>'
                    +'<td>'+currData[i].categoryDesc+'</td>'
                    +'<td>'
                    +  '<a class="layui-btn layui-btn-mini category_edit" data-id="'+currData[i].categoryId+'"><i class="iconfont icon-edit"></i> 编辑</a>'
                    +  '<a class="layui-btn layui-btn-danger layui-btn-mini category_del" data-id="'+currData[i].categoryId+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
                    +'</td>'
                    +'</tr>';
            }
        }else{
            dataHtml = '<tr><td colspan="7">暂无数据</td></tr>';
        }

        $(".category_content").html(dataHtml);
	}

	function getPageData(page, size) {
		var data = {};
		data.page = page;
		data.size = size;

        var index = top.layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : config.serverBaseURL + "category/list",
            type : "get",
			data : data,
            dataType : "json",
            success : function(result){
                top.layer.close(index);
                if(result.code === 0) {
                    categoryData = result.data;
                    renderCategoryList();
                }
            },
			error : function(e) {
                top.layer.msg("查询失败");
                top.layer.close(index);
			}
        })
    }

    function openCategoryAddView(title) {
        var index = layui.layer.open({
            title : title,
            type : 2,
            content : "categoryAdd.html",
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回品类列表', '.layui-layer-setwin .layui-layer-close', {
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
