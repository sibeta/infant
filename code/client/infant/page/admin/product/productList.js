layui.config({
    base : "../../../js/"
}).use(['form','layer','jquery','laypage','config'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery,
    	config = layui.config;

	var categoryList = [];
    var productData = '';
    getCategoryList();
    getPageData();


	//查询
	$(".search_btn").click(function(){
        var productName = $(".productName").val();
        if(productName != ''){
            getPageData(productName, 1);
        }else{
            getPageData();
        }
	})

	$(".productAdd_btn").click(function(){
        if(typeof window.product !== 'undefined') {
            delete window.product;
        }

        if(!window.categoryList) {
            window.categoryList = categoryList;
		}

        openProductAddView("添加商品信息");
	})
 
	//操作
	$("body").on("click",".product_edit",function(){  //编辑
        var productId = $(this).attr("data-id");
        var productList = productData.content;
        for(var i = 0; i < productList.length; i++){
            if(productList[i].productId == productId){
                window.product = productList[i];
                break;
            }
        }

        openProductAddView("编辑商品信息");
	})

	$("body").on("click",".product_del",function(){  //删除
        var _this = $(this);
        layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
            var productId = _this.attr("data-id");
            var data = {};
            data.productId = productId;

            $.ajax({
                url : config.serverBaseURL + "product/delete",
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

    function renderProductList(){
        var dataHtml = '';
        var currData = productData.content;
        if(currData.length !== 0){
            for(var i=0;i<currData.length;i++){
                dataHtml += '<tr>'
                    +'<td align="left">'+currData[i].categoryId+'</td>'
                    +'<td>'+currData[i].productName+'</td>'
                    +'<td>'+currData[i].productSize+'</td>'
                    +'<td>'+currData[i].productPrice+'</td>'
                    +'<td>'+currData[i].productStock+'</td>'
                    +'<td>'+currData[i].description+'</td>'
                    +'<td>'
                    +  '<a class="layui-btn layui-btn-mini product_edit" data-id="'+currData[i].productId+'"><i class="iconfont icon-edit"></i> 编辑</a>'
                    +  '<a class="layui-btn layui-btn-danger layui-btn-mini product_del" data-id="'+currData[i].productId+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
                    +'</td>'
                    +'</tr>';
            }
        }else{
            dataHtml = '<tr><td colspan="6">暂无数据</td></tr>';
        }


        $(".product_content").html(dataHtml);
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
                }
            },
            error : function(e) {
				//
            }
        })
    }

    function getPageData(productName, page, size) {
        var data = {};
        data.productName = productName;
        data.page = page;
        data.size = size;

        var index = top.layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : config.serverBaseURL + "product/list",
            type : "post",
            data : data,
            dataType : "json",
            success : function(result){
                top.layer.close(index);
                if(result.code === 0) {
                    productData = result.data;
                    renderProductList();
                }
            },
            error : function(e) {
                top.layer.msg("查询失败");
                top.layer.close(index);
            }
        })
    }

    function openProductAddView(title) {
        var index = layui.layer.open({
            title : title,
            type : 2,
            content : "productAdd.html",
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回商品列表', '.layui-layer-setwin .layui-layer-close', {
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
