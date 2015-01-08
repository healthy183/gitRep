package com.kang.model.vo;

public class SysUsersVo  implements java.io.Serializable {

	private int usrid;
	private String usrname;
	private String usrpwd;
	private Integer usrtype;
	
	public SysUsersVo() {
		super();
	}

	public int getUsrid() {
		return usrid;
	}

	public void setUsrid(int usrid) {
		this.usrid = usrid;
	}

	public String getUsrname() {
		return usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public String getUsrpwd() {
		return usrpwd;
	}

	public void setUsrpwd(String usrpwd) {
		this.usrpwd = usrpwd;
	}

	public Integer getUsrtype() {
		return usrtype;
	}

	public void setUsrtype(Integer usrtype) {
		this.usrtype = usrtype;
	}

	
	
	
}
