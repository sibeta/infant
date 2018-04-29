layui.config({
	base : "js/"
}).use(['jquery'],function(){
	$ = layui.jquery;

	$(".panel a").on("click",function(){
		window.parent.addTab($(this));
	})
})
