package cn.smbms.pojo;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 *  roleʵ����
 */
public class Role {

	
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleCode=" + roleCode + ", roleName=" + roleName + ", createBy=" + createBy
				+ ", creationDate=" + creationDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate
				+ ", userList=" + userList + "]";
	}
	private int id;//id
    private String roleCode;//����
    private String roleName;//��ɫ����
    private int createBy;//������id
    @JSONField(format="yyyy-MM-dd")
    private Date creationDate;//��������
    private int modifyBy;//����޸���id
    @JSONField(format="yyyy-MM-dd")
    private Date modifyDate;//����޸�����
    private List<User> userList;
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public Role() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
