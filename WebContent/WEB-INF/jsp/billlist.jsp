<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		//chaxun
		$(".viewBill").click(function() {
			alert("aaaaaaaaaaa");
			var obj = $(this);
			$.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/jsp/billview",
				data : {
					id : obj.attr("billid")
				},
				dataType : "json",
				success : function(data) {
					alert(data.billCode);
					$("#billCode").val(data.billCode);
					$("#productName").val(data.productName);
					$("#totalPrice").val(data.totalPrice);
					$("#creationDate").val(data.creationDate);

					if (data.isPayment == "1") {
						$("#isPayment").val("1");
					} else if (data.isPayment == "2") {
						$("#isPayment").val("2");
					}
				}
			})
		})
		
		$(".delete").click(function() {
			//alert("aaaaaaaaaaa");
			var obj = $(this);
			$.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/jsp/billDel",
				data : {
					id : obj.attr("billid")
				},
				dataType : "json",
				success : function(result) {
					if(result.aa>0){
						obj.parents("tr").remove();
					}else{
						alert("删除失败！");
					}
				}
			})
		})
		
		//删除bill
	})
</script>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>订单管理页面</span>
	</div>
	<div class="search">
		<form method="get"
			action="${pageContext.request.contextPath }/jsp/bill.do">
			<input name="method" value="query" class="input-text" type="hidden">
			<span>商品名称：</span> <input name="queryProductName" type="text"
				value="${queryProductName }"> <span>供应商：</span> <select
				name="queryProviderId">
				<c:if test="${providerList != null }">
					<option value="0">--请选择--</option>
					<c:forEach var="provider" items="${providerList}">
						<option
							<c:if test="${provider.id == queryProviderId }">selected="selected"</c:if>
							value="${provider.id}">${provider.proName}</option>
					</c:forEach>
				</c:if>
			</select> <span>是否付款：</span> <select name="queryIsPayment">
				<option value="0">--请选择--</option>
				<option value="1"
					${queryIsPayment == 1 ? "selected=\"selected\"":"" }>未付款</option>
				<option value="2"
					${queryIsPayment == 2 ? "selected=\"selected\"":"" }>已付款</option>
			</select> <input value="查 询" type="submit" id="searchbutton"> <a
				href="${pageContext.request.contextPath }/jsp/billadd.do">添加订单</a>
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
		<c:forEach var="bill" items="${billList}" varStatus="status">
			<tr>
				<td><span>${bill.billCode }</span></td>


				<td><span>${bill.productName }</span></td>
				<td><span></span></td>
				<td><span>${bill.totalPrice}</span></td>
				<td><span> <c:if test="${bill.isPayment == 1}">未付款</c:if>
						<c:if test="${bill.isPayment == 2}">已付款</c:if>
				</span></td>
				<td><span> <fmt:formatDate value="${bill.creationDate}"
							pattern="yyyy-MM-dd" />
				</span></td>
				<td ><span><a class="viewBill" href="javascript:;"
						billid=${bill.id } billcc=${bill.billCode }><img
							src="${pageContext.request.contextPath }/images/read.png"
							alt="查看" title="查看" /></a></span>
							
							 <span><a class="modifyBill"
						href="${pageContext.request.contextPath }/jsp/modify.do?billid=${bill.id }" billid=${bill.id } billcc=${bill.billCode } ><img
							src="${pageContext.request.contextPath }/images/xiugai.png"
							alt="修改" title="修改" /></a></span> 
							
							<span><a class="delete"
						href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img
							src="${pageContext.request.contextPath }/images/schu.png"
							alt="删除" title="删除" /></a></span></td>
			</tr>
		</c:forEach>
	</table>
	<div class="providerAdd">
		<div>
			<label for="billCode">订单编码：</label> <input type="text"
				name="billCode" class="text" id="billCode" value=""> <span
				id=hxj></span>
		</div>
		<div>
			<label for="productName">商品名称：</label> <input type="text"
				name="productName" id="productName" value="">
		</div>
		<div>
			<label for="totalPrice">总金额：</label> <input type="text"
				name="totalPrice" id="totalPrice" value="">
		</div>
		<div>
			<label for="creationDate">创建时间：</label> <input type="text"
				name="creationDate" id="creationDate" value="">
		</div>
		<div>
			<label>是否付款：</label> <input type="radio" name="isPayment" value="1"
				checked="checked">未付款 <input type="radio" name="isPayment"
				value="2">已付款
		</div>
	</div>

</div>

</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
	<div class="removerChid">
		<h2>提示</h2>
		<div class="removeMain">
			<p>你确定要删除该订单吗？</p>
			<a href="#" id="yes">确定</a> <a href="#" id="no">取消</a>
		</div>
	</div>
</div>

<%@include file="common/foot.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/billlist.js"></script>