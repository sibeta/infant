layui.config({
    base : "../../../js/"
}).use(['form','layer','jquery','laydate','config'],function(){
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        config = layui.config;

    var order = window.parent.order;
    if(typeof order !== 'undefined') {
        $(".orderId").val(order.orderId);
        $(".customerName").val(order.customerName);
        $(".customerName").attr("disabled", true);

        $(".totalPrice").val(order.totalPrice);
        $(".totalPrice").attr("disabled", true);

        var orderStatus = $(".orderStatus").find("option:contains('" + order.orderStatus + "')").val();
        $(".orderStatus").val(orderStatus);
        $(".orderStatus").attr("disabled", true);

        var payStatus = $(".payStatus").find("option:contains('" + order.payStatus + "')").val();
        $(".payStatus").val(payStatus);
        $(".payStatus").attr("disabled", true);

        form.render('select');

        $(".createTime").val(order.createTime);
        $(".createTime").attr("disabled", true);

        var orderDetailVOList = order.orderDetailVOList;
        var len = orderDetailVOList.length;
        for(var i = 0; i < len; i++) {
            var html = [];
            html.push('<tr>');
            html.push('<td align="left">' + orderDetailVOList[i].productName + '</td>');
            html.push('<td align="left">' + orderDetailVOList[i].productSize + '</td>');
            html.push('<td align="left">' + orderDetailVOList[i].productPrice + '</td>');
            html.push('<td align="left">' + orderDetailVOList[i].productQuantity + '</td>');
            html.push('</tr>');

            $(".order_detail_content").append($(html.join('')));
        }
    }

    $("body").on("click",".go_back",function(){
        layer.closeAll("iframe");
        return false;
    })

})
