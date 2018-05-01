layui.config({
    base : "../../../js/"
}).use(['form','layer','jquery','laydate','config'],function(){
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        config = layui.config;

    var productList = [];
    if(window.sessionStorage.getItem("productList")){
        productList = JSON.parse(window.sessionStorage.getItem("productList"));
    } else {
        productList.push({"productName":"纸尿裤"});
        productList.push({"productName":"拉拉裤"});
    }

    var order = window.parent.order;
    if(typeof order !== 'undefined') {
        $(".orderId").val(order.orderId);
        $(".customerName").val(order.customerName);
        $(".customerName").attr("disabled", true);

        $(".totalPrice").val(order.totalPrice);
        $(".totalPrice").attr("disabled", true);

        var orderStatus = $(".orderStatus").find("option:contains('" + order.orderStatus + "')").val();
        $(".orderStatus").val(orderStatus);

        var payStatus = $(".payStatus").find("option:contains('" + order.payStatus + "')").val();
        $(".payStatus").val(payStatus);

        form.render('select');
        //$(".customerLevel").find("option:contains('" + customer.customerLevel + "')").attr("selected","selected");
    }

    $("body").on("click",".addProduct",function(){
        var html = [];
        html.push('<tr>');
        html.push('<td align="left"><div class="layui-input-inline" style="width:100px;">');
        html.push('<select>');
        html.push(getProductOption());
        html.push('</select></div>');
        html.push('</td>');

        html.push('<td align="left"><div class="layui-input-inline" style="width:100px;">');
        html.push('<select>');
        html.push('<option value="0">无</option>');
        html.push('<option value="1">小码</option>');
        html.push('<option value="2">中码</option>');
        html.push('<option value="3">大码</option>');
        html.push('<option value="4">加大码</option>');
        html.push('<option value="5">加加大码</option>');
        html.push('<option value="6">加加加大码</option>');
        html.push('</select></div>');
        html.push('</td>');

        html.push('<td align="left">');
        html.push('<div class="layui-input-inline" style="width:100px;">');
        html.push('<input type="number" class="layui-input" lay-verify="required" placeholder="请输入单价"></div>');
        html.push('</td>');

        html.push('<td align="left">');
        html.push('<div class="layui-input-inline" style="width:100px;">');
        html.push('<input type="number" class="layui-input" lay-verify="required" placeholder="请输入数量"></div>');
        html.push('</td>');

        html.push('<td align="left">' + '<a class="layui-btn layui-btn-danger layui-btn-mini product_delete">删除</a>' + '</td>');
        html.push('</tr>');

        $(".product_content").append($(html.join('')));
        form.render('select');
    })

    $("body").on("click",".product_delete",function(e){
        $(this).parent().parent().remove();
    })

 	form.on("submit(addOrder)",function(data){
        var customerName = $(".customerName").val();

        var formData = {};
        formData.customerName = customerName;

        var items = [];
        var trs = $('#product_list tr');
        var len = trs.length;
        for(var i = 0; i < len; i++) {
            var tds = $(trs[i]).children('td');
            var product = {};
            product.productName = $(tds[0]).find("select").val();
            product.productSize = $(tds[1]).find("select").val();
            product.productPrice = $(tds[2]).find("input").val();
            product.productQuantity = $(tds[3]).find("input").val();

            items.push(product);
        }

        var itemStr = JSON.stringify(items);
        formData.items = itemStr;

        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});

        $.ajax({
            url : config.serverBaseURL + "order/create",
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

    function getProductOption(){
        var len = productList.length;
        var html = [];
        for(var i = 0; i < len; i++) {
            var product = productList[i];
            html.push("<option value='");
            html.push(product.productName);
            html.push("'>");
            html.push(product.productName);
            html.push("</option>");
        }

        return html.join('');
    }
})
