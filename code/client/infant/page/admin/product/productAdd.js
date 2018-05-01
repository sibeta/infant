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

    var product = window.parent.product;
    if(typeof product !== 'undefined') {
        $(".productId").val(product.productId);

        var categoryId = $(".categoryId").find("option:contains('" + product.categoryId + "')").val();
        $(".categoryId").val(categoryId);

        $(".productName").val(product.productName);

        var productSize = $(".productSize").find("option:contains('" + product.productSize + "')").val();
        $(".productSize").val(productSize);

        form.render('select');
        //$(".customerLevel").find("option:contains('" + customer.customerLevel + "')").attr("selected","selected");

        $(".productPrice").val(product.productPrice);
        $(".productStock").val(product.productStock);
        $(".description").val(product.description);
    }

 	form.on("submit(addProduct)",function(data){
        var productId = $(".productId").val();
        var categoryId = $(".categoryId").val();
        var productName = $(".productName").val();
        var productPrice = $(".productPrice").val();
        var productStock = $(".productStock").val();
        var productSize = $(".productSize").val();
        var description = $(".description").val();

        var formData = {};
        if(productId != "") {
            formData.productId = parseInt(productId);
        }
        formData.categoryId = categoryId;
        formData.productName = productName;
        formData.productPrice = productPrice;
        formData.productStock = productStock;
        formData.productSize = productSize;
        formData.description = description;

        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});

        $.ajax({
            url : config.serverBaseURL + "product/update",
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
