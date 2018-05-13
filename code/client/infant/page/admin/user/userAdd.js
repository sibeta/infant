layui.config({
    base : "../../../js/"
}).use(['form','layer','jquery','laydate','config'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery,
        laydate = layui.laydate,
		config = layui.config;

    var user = window.parent.user;
    if(typeof user !== 'undefined') {
        $(".userId").val(user.userId);
        $(".username").val(user.username);
        $(".username").attr("disabled", true);

        $(".useralias").val(user.useralias);
        $(".password").val(user.password);
        var role = $(".role").find("option:contains('" + user.role + "')").val();
        $(".role").val(role);

        form.render('select');
    }

 	form.on("submit(addUser)",function(data){
        var userId = $(".userId").val();
        var username = $(".username").val();
        var useralias = $(".useralias").val();
        var password = $(".password").val();
        var role = $(".role").val();

        var formData = {};
        if(userId != "") {
            formData.userId = parseInt(userId);
        }
        formData.username = username;
        formData.useralias = useralias;
        formData.password = password;
        formData.role = role;

        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});

        $.ajax({
            url : config.serverBaseURL + "user/update",
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
