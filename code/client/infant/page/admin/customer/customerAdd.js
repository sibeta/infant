layui.config({
    base : "../../../js/"
}).use(['form','layer','jquery','laydate','config'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery,
        laydate = layui.laydate,
		config = layui.config;

    var customer = window.parent.customer;
    if(typeof customer !== 'undefined') {
        $(".customerId").val(customer.customerId);
        $(".customerName").val(customer.customerName);
        var customerLevel = $(".customerLevel").find("option:contains('" + customer.customerLevel + "')").val();
        $(".customerLevel").val(customerLevel);
        form.render('select');
        //$(".customerLevel").find("option:contains('" + customer.customerLevel + "')").attr("selected","selected");

        $(".mobile").val(customer.mobile);
        $(".wechatName").val(customer.wechatName);
        $(".babyBirthday").val(customer.babyBirthday);
        $(".postAddress").val(customer.postAddress);
        $(".note").val(customer.note);
    }

 	form.on("submit(addCustomer)",function(data){
        var customerId = $(".customerId").val();
        var customerName = $(".customerName").val();
        var customerLevel = $(".customerLevel").val();
        var mobile = $(".mobile").val();
        var wechatName = $(".wechatName").val();
        var babyBirthday = $(".babyBirthday").val();
        var postAddress = $(".postAddress").val();
        var note = $(".note").val();


        var formData = {};
        if(customerId != "") {
            formData.customerId = parseInt(customerId);
        }
        formData.customerName = customerName;
        formData.customerLevel = customerLevel;
        formData.mobile = mobile;
        formData.wechatName = wechatName;
        formData.babyBirthday = babyBirthday;
        formData.postAddress = postAddress;
        formData.note = note;

        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});

        $.ajax({
            url : config.serverBaseURL + "customer/update",
            type : "post",
            data : formData,
            dataType : "json",
            success : function(result){
                top.layer.close(index);
                if(result.code == 0) {
                    top.layer.msg("操作成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }
            },
            error : function(e) {
                top.layer.close(index);
                var error = JSON.parse(e.responseText);
                if(error) {
                    top.layer.msg(error.message);
                } else {
                    top.layer.msg("操作失败");
                }
            }
        })

 		return false;
 	})
	
})
