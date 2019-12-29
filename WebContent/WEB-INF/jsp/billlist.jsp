<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
       <div class="location">
           <strong>你现在所在的位置是:</strong>
           <span>订单管理页面</span>
       </div>
       <div class="search">
       <form method="post" action="${pageContext.request.contextPath }/bill/billlist.action">
			<input type="hidden" id="ProviderId" value=${QueryProviderId }>
			<span>商品名称：</span>
			<input name="queryProductName" type="text" value="${queryProductName}">
			<span>供应商：</span>
			<select name="queryProviderId" id ="queryProviderId">
<%-- 				<option value="">--请选择--</option>
				<c:forEach items="${providerlist}" var="item">
					<option value="${item.id}" <c:if test="${QueryProviderId == item.id}">selected</c:if>>${item.proname }</option>
				</c:forEach> --%>
       		</select>
			<span>是否付款：</span>
			<select name="queryIsPayment">
				<option value="">--请选择--</option>
				<option value="1" ${queryIsPayment == 1 ? "selected=\"selected\"":"" }>未付款</option>
				<option value="2" ${queryIsPayment == 2 ? "selected=\"selected\"":"" }>已付款</option>
       		</select>
			 <input type="hidden" name="pageIndex" value="1"/>
			 <input	value="查 询" type="submit" id="searchbutton">
			 <a href="${pageContext.request.contextPath }/bill/billadd.action">添加订单</a>
		</form>
       </div>
       <!--账单表格 样式和供应商公用-->
      <table class="providerTable" cellpadding="0" cellspacing="0">
          <tr class="firstTr">
              <th width="10%">订单编码</th>
              <th width="20%">商品名称</th>
              <th width="10%">供应商</th>
              <th width="10%">订单金额</th>
              <th width="10%">是否付款</th>
              <th width="10%">创建时间</th>
              <th width="30%">操作</th>
          </tr>
          <c:forEach var="bill" items="${bill}" varStatus="status">
				<tr>
					<td>
					<span>${bill.billcode}</span>
					</td>
					<td>
					<span>${bill.productname}</span>
					</td>
					<td>
					<c:forEach items="${providerlist}" var="item">
						<c:if test="${bill.providerid == item.id}"><span>${item.proname}</span></c:if>
					</c:forEach>
					<%-- <span>${bill.productname}</span> --%>
					</td>
					<td>
					<span>${bill.totalprice}</span>
					</td>
					<td>
					<span>
						<c:if test="${bill.ispayment == 1}">未付款</c:if>
						<c:if test="${bill.ispayment == 2}">已付款</c:if>
					</span>
					</td>
					<td>
					<span>
					<fmt:formatDate value="${bill.creationdate}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					<td>
					<span><a class="viewBill" href="javascript:;" billid=${bill.id } billcc=${bill.billcode }><img src="${pageContext.request.contextPath }/statics/images/read.png" alt="查看" title="查看"/></a></span>
					<span><a class="modifyBill" href="javascript:;" billid=${bill.id } billcc=${bill.billcode }><img src="${pageContext.request.contextPath }/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>
					<span><a class="deleteBill" href="javascript:;" billid=${bill.id } billcc=${bill.billcode }><img src="${pageContext.request.contextPath }/statics/images/schu.png" alt="删除" title="删除"/></a></span>
					</td>
				</tr>
			</c:forEach>
      </table>
 			<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
		  	<c:import url="rollpage.jsp">
	          	<c:param name="totalCount" value="${totalCount}"/>
	          	<c:param name="currentPageNo" value="${currentPageNo}"/>
	          	<c:param name="totalPageCount" value="${totalPageCount}"/>
          	</c:import>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/billlist5.js"></script>