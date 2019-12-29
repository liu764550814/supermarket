$(function(){
	
	$(".deleteRole").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
	
		var obj = $(this);
		if(confirm("你确定要Role【"+obj.attr("roleid")+"】吗？")){
	
			$.ajax({
				type:"POST",
				url:"/supermarket/role/deleterole.action",
				data:{"roleid":obj.attr("roleid")},//"1123234"},//
				dataType:"json",
				success:function(data){
					//data = JSON.parse(data);
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除Role【"+obj.attr("roleid")+"】失败");
					}else if(data.delResult == "notexist"){
						alert("对不起，Role【"+obj.attr("roleid")+"】不存在");
					}else{
						alert("对不起，该Role【"+obj.attr("roleid")+"】下有【"+data.delResult+"】其他子Role，不能删除");
					}
				},
				error:function(data){
					alert("对不起，删除失败");
					
				}
			});
		}
	});
	
	$(".modifyRole").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/role/updateRole.action?roleid="+ obj.attr("roleid");
	});
});