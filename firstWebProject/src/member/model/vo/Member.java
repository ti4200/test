package member.model.vo;

import java.sql.Date;

public class Member implements java.io.Serializable{
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String phone;
	private String address;
	private String gender;
	private Date birth;
	private String hobby;
	
	public Member(){}

	public Member(String userId, String userPwd, String userName, String email, String phone, String address,
			String gender, Date birth, String hobby) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.birth = birth;
		this.hobby = hobby;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	
	@Override
	public String toString(){
		return this.userId + ", " + this.userPwd + ", " + this.userName + ", " + 
				this.email + ", " + this.phone + ", " + this.address + ", " + 
				this.gender + ", " + this.birth + ", " + this.hobby;
	}
}







