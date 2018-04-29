layui.config({
	base : "../../../js/"
}).use(['form','layer','jquery','config'],function(){
	var form = layui.form()
		$ = layui.jquery,
        config = layui.config;

	var category = window.parent.category;
	if(typeof category !== 'undefined') {
        $(".categoryId").val(category.categoryId);
        $(".categoryName").val(category.categoryName);
        $(".categoryDesc").val(category.categoryDesc);
	}

 	form.on("submit(addLinks)",function(data){
        var categoryId = $(".categoryId").val();
 		var categoryName = $(".categoryName").val();
 		var categoryDesc = $(".categoryDesc").val();

 		var formData = {};
 		if(categoryId != "") {
 			formData.categoryId = parseInt(categoryId);
		}
        formData.categoryName = categoryName;
        formData.categoryDesc = categoryDesc;

 		//弹出loading
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});

        $.ajax({
            url : config.serverBaseURL + "category/update",
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
