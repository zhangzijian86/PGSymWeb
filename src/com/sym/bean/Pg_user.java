package com.sym.bean;

public class Pg_user {
	
	private String UserID;//ID
	private String UserCode;//编码
	private String UserName;//姓名
	private String Password;//密码
	private String PasswordKey;//加解密密钥
	private String PasswordIV;//加解密矢量
	private String UserClass;//用户类别（1，系统用户，2，员工）
	private String Gender;//性别:1,男，2,女
	private String ISDN;//身份证号
	private String Telephone;//电话
	private String Mobile;//手机
	private String Fax;//传真
	private String Email;//电子邮件
	private String Address;//住址
	private String Photo;//相片
	private String Age;//年龄
	private String Birthday;//出生日期
	private String Remark;//备注
	private String Status;//用户状态(1,有效，0,禁用，-1,删除)
	private String CreatedBy;//创建人
	private String CreatedDate;//创建时间
	private String ModifiedBy;//修改人
	private String ModifiedDate;//修改时间
	
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getUserCode() {
		return UserCode;
	}
	public void setUserCode(String userCode) {
		UserCode = userCode;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getPasswordKey() {
		return PasswordKey;
	}
	public void setPasswordKey(String passwordKey) {
		PasswordKey = passwordKey;
	}
	public String getPasswordIV() {
		return PasswordIV;
	}
	public void setPasswordIV(String passwordIV) {
		PasswordIV = passwordIV;
	}
	public String getUserClass() {
		return UserClass;
	}
	public void setUserClass(String userClass) {
		UserClass = userClass;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getISDN() {
		return ISDN;
	}
	public void setISDN(String iSDN) {
		ISDN = iSDN;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getFax() {
		return Fax;
	}
	public void setFax(String fax) {
		Fax = fax;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthday) {
		Birthday = birthday;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public String getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}
	public String getModifiedBy() {
		return ModifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}
	public String getModifiedDate() {
		return ModifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		ModifiedDate = modifiedDate;
	}
	
}
