<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm"%>
<%@include file="../common/head.jsp"%>

<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>供应商管理页面 >> 供应商添加页面</span>
	</div>
	<div class="providerAdd">
		<fm:form id="providerForm" name="providerForm" method="post"
			action="${pageContext.request.contextPath }/provider/addprovider.html"
			modelAttribute="provider" enctype="multipart/form-data">

			<!--div的class 为error是验证错误，ok是验证成功-->
			<div class="">
				<label for="proCode">供应商编码：</label>
				<fm:input path="proCode" />
				<!-- 放置提示信息 -->
				<fm:errors path="proCode" cssStyle="color:red" />
			</div>
			<div>
				<label for="proName">供应商名称：</label>
				<fm:input path="proName" />
				<fm:errors path="proName" cssStyle="color:red" />
			</div>
			<div>
				<label for="proContact">联系人：</label>
				<fm:input path="proContact" />
				<fm:errors path="proContact" cssStyle="color:red" />
			</div>
			<div>
				<label for="proPhone">联系电话：</label>
				<fm:input path="proPhone" />
				<fm:errors path="proPhone" cssStyle="color:red" />
			</div>
			<div>
				<label for="proAddress">联系地址：</label> <input type="text"
					name="proAddress" id="proAddress" value="">
			</div>
			<div>
				<label for="proFax">传真：</label>
				<fm:input path="proFax" />
				<fm:errors path="proFax" cssStyle="color:red" />
			</div>
			<div>
				<label for="proDesc">描述：</label> <input type="text" name="proDesc"
					id="proDesc" value="">
			</div>
			 <div>
				<input type="hidden" id="errorinfo" value="${uploadFileError}" /> <label
					for="a_companyLicPicPath">企业营业执照：</label> <input type="file"
					name="a_companyLicPicPath" id="a_companyLicPicPath" /> <font color="red"></font>
			</div>
			<div>
				<input type="hidden" id="errorinfo_oc" value="${uploadOcError}" /> <label
					for="a_orgCodePicPath">组织机构代码证：</label> <input type="file"
					name="a_orgCodePicPath" id="a_orgCodePicPath" /> <font color="red"></font>
			</div> 
			<div class="providerAddBtn">
				<input type="button" name="add" id="add" value="保存"> <input
					type="button" id="back" name="back" value="返回">
			</div>

		</fm:form>
	</div>
</div>
</section>
<%@include file="../common/foot.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/provideradd.js"></script>
