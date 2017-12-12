package cn.smbms.pojo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.Timeout;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * �û���
 *
 */
public class User {

	@Override
	public String toString() {
		return "User [id=" + id + ", userCode=" + userCode + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", gender=" + gender + ", birthday=" + birthday + ", phone=" + phone + ", address=" + address
				+ ", userRole=" + userRole + ", createBy=" + createBy + ", createDate=" + createDate + ", modifyBy="
				+ modifyBy + ", modifyDate=" + modifyDate + ", role=" + role + "]";
	}
	private int id;//id
    private String userCode;//���
    private String userName;//�û���
    private String userPassword;//����
    private int gender;//�Ա�
    /**
     * ͨ��ע���ǩʵ��ʱ��birthday��ʱ�����ͺ͸�ʽ
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;//����
    private String phone;//��ȯ
    private String address;//��ַ
    private int userRole;//��ɫid
    private int createBy;//�����˻���id
    private Date createDate;//��������
    private int modifyBy;//����޸���id
    private Date modifyDate;//����޸�����
    private Role role;
    private int age;
    
    
	public int getAge() {
		age=1;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		//ȡ��ǰʱ���������
		Calendar c=Calendar.getInstance();
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH);
		int day=c.get(Calendar.DAY_OF_MONTH);
		//ȡ���յ�������
		Calendar cB=Calendar.getInstance();
		cB.setTime(birthday);
		int yearB=cB.get(Calendar.YEAR);
		int monthB=cB.get(Calendar.MONTH);
		int dayB=cB.get(Calendar.DAY_OF_MONTH);
		age=year-yearB-1;
		if(month>monthB)
			age++;
		else if (month==monthB){
			if(day>dayB)
				age++;
		}
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
