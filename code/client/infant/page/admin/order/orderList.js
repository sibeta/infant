layui.config({
    base : "../../../js/"
}).use(['form','layer','jquery','laypage','config'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery,
    	config = layui.config;

    var orderData = '';
    var productList = [];
    //getProductList();
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

	$(".orderAdd_btn").click(function(){
	    if(window.order) {
	        delete window.order;
        }
        openOrderView("新建订单", "orderAdd.html");
	})

    //操作
    $("body").on("click",".order_detail",function(){
        var orderId = $(this).attr("data-id");
        var data = {};
        data.orderId = orderId;

        var index = top.layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : config.serverBaseURL + "order/detail",
            type : "post",
            data : data,
            dataType : "json",
            success : function(result){
                top.layer.close(index);
                if(result.code === 0) {
                    var order = result.data;
                    window.order = order;

                    openOrderView("订单详情信息", "orderDetail.html");
                }
            },
            error : function(e) {
                top.layer.msg("查询失败");
                top.layer.close(index);
            }
        })
    })

	//操作
	$("body").on("click",".order_finish",function(){
        var _this = $(this);
        layer.confirm('确定完结此订单？',{icon:3, title:'提示信息'},function(index){
            var orderId = _this.attr("data-id");
            var data = {};
            data.orderId = orderId;

            $.ajax({
                url : config.serverBaseURL + "order/finish",
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

	$("body").on("click",".order_cancel",function(){
        var _this = $(this);
        layer.confirm('确定取消此订单？',{icon:3, title:'提示信息'},function(index){
            var orderId = _this.attr("data-id");
            var data = {};
            data.orderId = orderId;

            $.ajax({
                url : config.serverBaseURL + "order/cancel",
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

    function renderOrderList(){
        var dataHtml = '';
        var currData = orderData.content;
        if(currData.length !== 0){
            for(var i=0;i<currData.length;i++){
                dataHtml += '<tr>'
                    +'<td align="left">'+currData[i].customerName+'</td>'
                    +'<td>'+currData[i].totalPrice+'</td>'
                    +'<td>'+currData[i].orderStatus+'</td>'
                    +'<td>'+currData[i].payStatus+'</td>'
                    +'<td>'+currData[i].createTime+'</td>'
                    +'<td>'
                    +  '<a class="layui-btn layui-btn-warm layui-btn-mini order_detail" data-id="'+currData[i].orderId+'"></i> 详情</a>';

                if(currData[i].orderStatus === "新订单") {
                    dataHtml += '<a class="layui-btn layui-btn-normal layui-btn-mini order_finish" data-id="'+currData[i].orderId+'"></i> 完结</a>';
                    dataHtml += '<a class="layui-btn layui-btn-danger layui-btn-mini order_cancel" data-id="'+currData[i].orderId+'"> 取消</a>';
                }

                dataHtml += '</td></tr>';
            }
        }else{
            dataHtml = '<tr><td colspan="9">暂无数据</td></tr>';
        }

        $(".order_content").html(dataHtml);
    }

    function getProductList() {
        $.ajax({
            url : config.serverBaseURL + "product/listall",
            type : "get",
            dataType : "json",
            success : function(result){
                debugger;
                if(result.code === 0) {
                    productList = result.data;
                    window.sessionStorage.setItem("productList", JSON.stringify(productList));
                }
            },
            error : function(e) {
                //
            }
        })
    }

    function getPageData(customerName, page, size) {
        var data = {};
        data.customerName = customerName;
        data.page = page;
        data.size = size;

        var index = top.layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : config.serverBaseURL + "order/list",
            type : "post",
            data : data,
            dataType : "json",
            success : function(result){
                top.layer.close(index);
                if(result.code === 0) {
                    orderData = result.data;
                    renderOrderList();
                }
            },
            error : function(e) {
                top.layer.msg("查询失败");
                top.layer.close(index);
            }
        })
    }

    function openOrderView(title, htmlName) {
        var index = layui.layer.open({
            title : title,
            type : 2,
            content : htmlName,
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回订单列表', '.layui-layer-setwin .layui-layer-close', {
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
