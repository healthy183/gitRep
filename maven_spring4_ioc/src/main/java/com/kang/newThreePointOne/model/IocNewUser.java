package com.kang.newThreePointOne.model;


public class IocNewUser implements java.io.Serializable {

	public Integer usrId;
	
	public String usrName;
	
	public String usrSwd;
	
	/*@Null
	public String nullStr;

	public String getNullStr() {
		return nullStr;
	}

	public void setNullStr(String nullStr) {
		this.nullStr = nullStr;
	}*/

	public IocNewUser() {
		super();
	}

	public IocNewUser(String usrName, String usrSwd) {
		super();
		this.usrName = usrName;
		this.usrSwd = usrSwd;
	}

	
	
	
	public Integer getUsrId() {
		return usrId;
	}

	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrSwd() {
		return usrSwd;
	}

	public void setUsrSwd(String usrSwd) {
		this.usrSwd = usrSwd;
	}

}
