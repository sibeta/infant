layui.config({
    base : "../../../js/"
}).use(['form','layer','jquery','config'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery,
		config = layui.config;

	form.on("submit(login)",function(data){

        var index = top.layer.msg('请稍候',{icon: 16,time:false,shade:0.8});
        var data = {};
        var username = $(".username").val();
        var password = $(".password").val();
        data.username = username;
        data.password = password;

        $.ajax({
            url : config.serverBaseURL + "user/login",
            type : "post",
            data : data,
            dataType : "json",
            success : function(result){
                top.layer.close(index);

                if(result.code === 0 && result.data == true) {
                    window.location.href = "../../../index.html";
                } else {
                    top.layer.msg(result.msg);
				}
            },
            error : function(e) {
                top.layer.msg("登录失败");
                top.layer.close(index);
            }
        })

		return false;
	})
})
