layui.config({
    base : "../../../js/"
}).use(['form','layer','jquery','laydate','config'],function(){
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        config = layui.config;

 	var categoryList = [];
    if(window.sessionStorage.getItem("categoryList")){
        categoryList = JSON.parse(window.sessionStorage.getItem("categoryList"));
    }
    renderCategoryList();

    var stock = window.parent.stock;
    if(typeof stock !== 'undefined') {
        $(".stockId").val(stock.stockId);

        var type = $(".type").find("option:contains('" + stock.type + "')").val();
        $(".type").val(type);

        var categoryId = $(".categoryId").find("option:contains('" + stock.categoryId + "')").val();
        $(".categoryId").val(categoryId);

        form.render('select');
        //$(".customerLevel").find("option:contains('" + customer.customerLevel + "')").attr("selected","selected");

        $(".amount").val(stock.amount);
        $(".unitPrice").val(stock.unitPrice);
        $(".extraCharges").val(stock.extraCharges);
        $(".stockDate").val(stock.stockDate);
        $(".aogDate").val(stock.aogDate);
        $(".note").val(stock.note);
    }

 	form.on("submit(addStock)",function(data){
        var stockId = $(".stockId").val();
        var categoryId = $(".categoryId").val();
        var type = $(".type").val();
        var amount = $(".amount").val();
        var unitPrice = $(".unitPrice").val();
        var extraCharges = $(".extraCharges").val();
        var stockDate = $(".stockDate").val();
        var aogDate = $(".aogDate").val();
        var note = $(".note").val();

        var formData = {};
        if(stockId != "") {
            formData.stockId = parseInt(stockId);
        }
        formData.categoryId = categoryId;
        formData.type = type;
        formData.amount = amount;
        formData.unitPrice = unitPrice;
        formData.extraCharges = extraCharges;
        formData.stockDate = stockDate;
        formData.aogDate = aogDate;
        formData.note = note;

        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});

        $.ajax({
            url : config.serverBaseURL + "stock/update",
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

    function renderCategoryList(){
        $(".categoryList").empty();
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
})
