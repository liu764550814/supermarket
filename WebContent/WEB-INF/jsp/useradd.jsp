<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form"%>

<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>用户管理页面 >> 用户添加页面</span>
	</div>
	<div class="providerAdd">
		<div align="center" style="font-size:12px;color:red">${msg }</div>
		<form id="userForm" name="userForm" method="post"
			action="${pageContext.request.contextPath }/user/saveuser.action"><!-- enctype="multipart/form-data" -->
			<!--div的class 为error是验证错误，ok是验证成功-->
			<div>
				<fm:errors path="userCode" />
				<label for="userCode">用户编码：</label> <input type="text"
					name="usercode" id="userCode" value="">
				<!-- 放置提示信息 -->
				<font color="red"></font>
			</div>
			<div>
				<fm:errors path="userName" />
				<label for="userName">用户名称：</label> <input type="text"
					name="username" id="userName" value=""> <font color="red"></font>
			</div>
			<div>
				<fm:errors path="userPassword" />
				<label for="userPassword">用户密码：</label> <input type="password"
					name="userpassword" id="userPassword" value=""> <font
					color="red"></font>
			</div>
			<div>
				<label for="ruserPassword">确认密码：</label> <input type="password"
					name="ruserpassword" id="ruserPassword" value=""> <font
					color="red"></font>
			</div>
			<div>
				<label>用户性别：</label> <select name="gender" id="gender">
					<option value="1" selected="selected">男</option>
					<option value="2">女</option>
				</select>
			</div>
			<div>
				<fm:errors path="birthday" />
				<label for="birthday">出生日期：</label> <input type="text" Class="Wdate"
					id="birthday" name="sbirthday" readonly="readonly"
					onclick="WdatePicker();"> <font color="red"></font>
			</div>
			<div>
				<label for="phone">用户电话：</label> <input type="text" name="phone"
					id="phone" value=""> <font color="red"></font>
			</div>
			<div>
				<label for="address">用户地址：</label> <input name="address"
					id="address" value="">
			</div>
			<div>
				<label>用户角色：</label>
				<!-- 列出所有的角色分类 -->
				<select name="userrole" id="userRole">
				</select> <font color="red"></font>
			</div>
			<%-- <div>
				<input type="hidden" id="errorinfo" value="${uploadFileError}" /> <label
					for="a_idPicPath">证件照：</label> <input type="file" name="attachs"
					id="a_idPicPath" /> <font color="red"></font>
			</div>
			<div>
				<input type="hidden" id="errorinfo_wp" value="${uploadWpError}" />
				<label for="a_workPicPath">工作证照片：</label> <input type="file"
					name="attachs" id="a_workPicPath" /> <font color="red"></font>
			</div>--%>
			<div class="providerAddBtn">
				<input type="button" name="add" id="add" value="保存"> <input
					type="button" id="back" name="back" value="返回">
			</div> 
		</form>
	</div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/useradd2.js"></script>
