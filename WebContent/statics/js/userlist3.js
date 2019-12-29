var userObj;
//用户管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteUser(obj){
	alert("1111");
	$.ajax({
		type:"POST",
		url:"/supermarket/user/deluser.action",
		data:{"userid":obj.attr("userid")},
		dataType:"json",
		success:function(data){
			if(data.result == "true"){//删除成功：移除删除行
				alert("用户【"+obj.attr("username")+"】删除成功！");
				//changeDLGContent("用户【"+obj.attr("username")+"】删除成功！");
				location.href=path+"/user/userlist.action";
			}else if(data.result == "false"){//删除失败
				alert("对不起，删除用户【"+obj.attr("username")+"】失败");
				//changeDLGContent("对不起，删除用户【"+obj.attr("username")+"】失败");
			}else if(data.result == "notexist"){
				//alert("对不起，用户【"+obj.attr("username")+"】不存在");
				alert("对不起，用户【"+obj.attr("username")+"】不存在");
				//changeDLGContent("对不起，用户【"+obj.attr("username")+"】不存在");
			}
		},
		error:function(data){
			alert("111");
			changeDLGContent("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	/**
	 * bind、live、delegate
	 * on
	 */
	/*$(".viewUser").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法

		window.location.href=path+"/user/view.html/"+ obj.attr("userid");
	});*/
	$(".viewUser").click(function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		$.ajax({
			type:"POST",
			url:"/supermarket/user/view.action",
			data:{"uid":obj.attr("userid")},
			dataType:"json",
			success:function(data){
				if(data=="nodata"){
					alert("没有数据！");
				}else if(data=="failed"){
					alert("操作超时！");
				}else{
					$("#v_userCode").val(data.usercode);
					$("#v_userName").val(data.username);
					if(data.gender == "1"){
						$("#v_gender").val("女");
					}else if(data.gender == "2"){
						$("#v_gender").val("男");
					}
					$("#v_birthday").val(data.sbirthday);
					$("#v_phone").val(data.phone);
					$("#v_address").val(data.address);
					$("#v_userRoleName").val(data.rolename);
				}
			},
			error:function(data){
				alert(data+"error!");
			}
		});
	});


	$(".modifyUser").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/user/modify.action?uid="+ obj.attr("userid");
	});
	$('#no').click(function () {
		cancleBtn();
	});

	$('#yes').click(function () {
		deleteUser(userObj);
	});

	$(".deleteUser").on("click",function(){
		userObj = $(this);
		changeDLGContent("你确定要删除用户【"+userObj.attr("username")+"】吗？");
		openYesOrNoDLG();
	});
});