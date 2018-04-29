layui.config({
	base : "../../../js/"
}).use(['form','layer','jquery','laypage','config'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery,
        config = layui.config;

	var customerData = '';
	getPageData();

	//查询
	$(".search_btn").click(function(){
		var customerName = $(".search_input").val();
		if(customerName != ''){
			getPageData(customerName, 1);
		}else{
			getPageData();
		}
	})

	//添加
	$(".customerAdd_btn").click(function(){
        if(typeof window.customer !== 'undefined') {
            delete window.customer;
        }
        openCustomerAddView("添加客户信息");
	})

	//操作
	$("body").on("click",".customer_edit",function(){
        var customerId = $(this).attr("data-id");
        var customerList = customerData.content;
        for(var i = 0; i < customerList.length; i++){
            if(customerList[i].customerId == customerId){
                window.customer = customerList[i];
                break;
            }
        }

        openCustomerAddView("编辑品类信息");
	})

	$("body").on("click",".customer_del",function(){  //删除
        var _this = $(this);
        layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
            var customerId = _this.attr("data-id");
            var data = {};
            data.customerId = customerId;

            $.ajax({
                url : config.serverBaseURL + "customer/delete",
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

    function renderCustomerList(){
        var dataHtml = '';
        var currData = customerData.content;
        if(currData.length !== 0){
            for(var i=0;i<currData.length;i++){
                dataHtml += '<tr>'
                    +'<td align="left">'+currData[i].customerName+'</td>'
                    +'<td>'+currData[i].customerLevel+'</td>'
                    +'<td>'+currData[i].mobile+'</td>'
                    +'<td>'+currData[i].wechatName+'</td>'
                    +'<td>'+currData[i].babyBirthday+'</td>'
                    +'<td>'+currData[i].postAddress+'</td>'
                    +'<td>'
                    +  '<a class="layui-btn layui-btn-mini customer_edit" data-id="'+currData[i].customerId+'"><i class="iconfont icon-edit"></i> 编辑</a>'
                    +  '<a class="layui-btn layui-btn-danger layui-btn-mini customer_del" data-id="'+currData[i].customerId+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
                    +'</td>'
                    +'</tr>';
            }
        }else{
            dataHtml = '<tr><td colspan="7">暂无数据</td></tr>';
        }

        $(".customer_content").html(dataHtml);
    }

    function getPageData(customerName, page, size) {
        var data = {};
        data.customerName = customerName;
        data.page = page;
        data.size = size;

        var index = top.layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : config.serverBaseURL + "customer/list",
            type : "post",
            data : data,
            dataType : "json",
            success : function(result){
                top.layer.close(index);
                if(result.code === 0) {
                    customerData = result.data;
                    renderCustomerList();
                }
            },
            error : function(e) {
                top.layer.msg("查询失败");
                top.layer.close(index);
            }
        })
    }

    function openCustomerAddView(title) {
        var index = layui.layer.open({
            title : title,
            type : 2,
            content : "customerAdd.html",
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
