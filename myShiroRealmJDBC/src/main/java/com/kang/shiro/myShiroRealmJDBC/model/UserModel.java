package com.kang.shiro.myShiroRealmJDBC.model;

public class UserModel  implements java.io.Serializable {

		
		private Long  userId;
		private String userName;
		private String userPassword;
		private String userSalt;
		
		private  Boolean isLocked = Boolean.FALSE;
		
		public UserModel(){}

		public UserModel(String userName, String userPassword) {
			super();
			this.userName = userName;
			this.userPassword = userPassword;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
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

		public String getUserSalt() {
			return userSalt;
		}

		public void setUserSalt(String userSalt) {
			this.userSalt = userSalt;
		}

		public Boolean getIsLocked() {
			return isLocked;
		}

		public void setIsLocked(Boolean isLocked) {
			this.isLocked = isLocked;
		}
		
		public String getCreadentialsSalt(){
			return userName+userSalt;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((isLocked == null) ? 0 : isLocked.hashCode());
			result = prime * result
					+ ((userId == null) ? 0 : userId.hashCode());
			result = prime * result
					+ ((userName == null) ? 0 : userName.hashCode());
			result = prime * result
					+ ((userPassword == null) ? 0 : userPassword.hashCode());
			result = prime * result
					+ ((userSalt == null) ? 0 : userSalt.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UserModel other = (UserModel) obj;
			if (isLocked == null) {
				if (other.isLocked != null)
					return false;
			} else if (!isLocked.equals(other.isLocked))
				return false;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			if (userName == null) {
				if (other.userName != null)
					return false;
			} else if (!userName.equals(other.userName))
				return false;
			if (userPassword == null) {
				if (other.userPassword != null)
					return false;
			} else if (!userPassword.equals(other.userPassword))
				return false;
			if (userSalt == null) {
				if (other.userSalt != null)
					return false;
			} else if (!userSalt.equals(other.userSalt))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "UserModel [userId=" + userId + ", userName=" + userName
					+ ", userPassword=" + userPassword + ", userSalt="
					+ userSalt + ", isLocked=" + isLocked + "]";
		}
		
		
		
		
		
	
	
}
