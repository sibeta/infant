layui.config({
    base : "../../../js/"
}).use(['form','layer','jquery','laypage','config'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery,
    	config = layui.config;

	var categoryList = [];
    var stockData = '';
    getCategoryList();
    getPageData();


	//查询
	$(".search_btn").click(function(){
        var categoryId = $(".categoryId").val();
        if(categoryId != ''){
            getPageData(categoryId, 1);
        }else{
            getPageData();
        }
	})

	$(".stockAdd_btn").click(function(){
        if(typeof window.stock !== 'undefined') {
            delete window.stock;
        }

        if(!window.categoryList) {
            window.categoryList = categoryList;
		}

        openStockAddView("添加进货信息");
	})
 
	//操作
	$("body").on("click",".stock_edit",function(){  //编辑
        var stockId = $(this).attr("data-id");
        var stockList = stockData.content;
        for(var i = 0; i < stockList.length; i++){
            if(stockList[i].stockId == stockId){
                window.stock = stockList[i];
                break;
            }
        }

        openStockAddView("编辑进货信息");
	})

	$("body").on("click",".stock_del",function(){  //删除
        var _this = $(this);
        layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
            var customerId = _this.attr("data-id");
            var data = {};
            data.stockId = stockId;

            $.ajax({
                url : config.serverBaseURL + "stock/delete",
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
		var len = categoryList.length;
		for(var i = 0; i < len; i++) {
			var category = categoryList[i];
			var html = [];
			html.push("<option value='");
			html.push(category.categoryId);
			html.push("'>");
			html.push(category.categoryName);
			html.push("</option>");
        	$(".categoryList").append($(html.join('')));
		}

        form.render('select');
    }

    function renderStockList(){
        var dataHtml = '';
        var currData = stockData.content;
        if(currData.length !== 0){
            for(var i=0;i<currData.length;i++){
                dataHtml += '<tr>'
                    +'<td align="left">'+currData[i].categoryId+'</td>'
                    +'<td>'+currData[i].type+'</td>'
                    +'<td>'+currData[i].amount+'</td>'
                    +'<td>'+currData[i].unitPrice+'</td>'
                    +'<td>'+currData[i].extraCharges+'</td>'
                    +'<td>'+currData[i].stockDate+'</td>'
                    +'<td>'+currData[i].aogDate+'</td>'
                    +'<td>'+currData[i].note+'</td>'
                    +'<td>'
                    +  '<a class="layui-btn layui-btn-mini stock_edit" data-id="'+currData[i].stockId+'"><i class="iconfont icon-edit"></i> 编辑</a>'
                    +  '<a class="layui-btn layui-btn-danger layui-btn-mini stock_del" data-id="'+currData[i].stockId+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
                    +'</td>'
                    +'</tr>';
            }
        }else{
            dataHtml = '<tr><td colspan="9">暂无数据</td></tr>';
        }


        $(".stock_content").html(dataHtml);
    }

    function getCategoryList() {
        $.ajax({
            url : config.serverBaseURL + "category/listcategory",
            type : "get",
            dataType : "json",
            success : function(result){
                if(result.code === 0) {
                    categoryList = result.data;
                    window.sessionStorage.setItem("categoryList", JSON.stringify(categoryList));
                    renderCategoryList();
                }
            },
            error : function(e) {
				//
            }
        })
    }

    function getPageData(categoryId, page, size) {
        var data = {};
        data.categoryId = categoryId;
        data.page = page;
        data.size = size;

        var index = top.layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : config.serverBaseURL + "stock/list",
            type : "post",
            data : data,
            dataType : "json",
            success : function(result){
                top.layer.close(index);
                if(result.code === 0) {
                    stockData = result.data;
                    renderStockList();
                }
            },
            error : function(e) {
                top.layer.msg("查询失败");
                top.layer.close(index);
            }
        })
    }

    function openStockAddView(title) {
        var index = layui.layer.open({
            title : title,
            type : 2,
            content : "stockAdd.html",
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回进货列表', '.layui-layer-setwin .layui-layer-close', {
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
