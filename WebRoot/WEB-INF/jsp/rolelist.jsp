<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>角色管理页面</span>
            </div>
            <div class="search">
           		<a href="${pageContex.request.contextPath }/ch102/action/addrole.html" >添加角色</a>
            </div>
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">角色编码</th>
                    <th width="20%">角色名称</th>
                    <th width="10%">创建时间</th>
                    <th width="30%">操作</th>
                </tr>
                   <c:forEach var="role" items="${roleList }" varStatus="status">
					<tr>
						<td>
						<span>${role.roleCode }</span>
						</td>
						<td>
						<span>${role.roleName }</span>
						</td>
						<td>
						<span><fmt:formatDate value="${role.creationDate}" pattern="yyyy-MM-dd"/></span>
						</td>
						<td>
						<span><a class="modifyRole" href="${pageContext.request.contextPath }/role/updaterole.html/${role.id }" onclick="return aClick()" roleid=${role.id } rolename=${role.roleName }><img src="${pageContext.request.contextPath }/statics/images/xiugai.png" alt="修改" title="修改" class="change"/></a></span>
						<span><a class="deleteRole" href="${pageContext.request.contextPath }/role/deleterole.html/${role.id }" onclick="return aClick(${role.id })"  roleid=${role.id } rolename=${role.roleName }><img src="${pageContext.request.contextPath }/statics/images/schu.png" alt="删除" title="删除" class="delete"/></a></span>
						</td>
					</tr>
				</c:forEach>
			</table>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao" id="zhezhao"></div>
<div class="remove" id="removeRole">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该角色吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/rolelistt.js"></script>
