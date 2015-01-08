package com.kang.shiro.permission;

import org.apache.shiro.authz.Permission;

import com.alibaba.druid.util.StringUtils;

public class BitPermission implements Permission {

	private String resourceIdentify;
	private int permissionBit;
	private String instanceId;

	public BitPermission(String permissionString) {

		String[] array = permissionString.split("\\+");

		int arrayLength = array.length;

		if (arrayLength > 1) {
			resourceIdentify = array[1];
		}

		if (StringUtils.isEmpty(resourceIdentify)) {
			resourceIdentify = "*";
		}

		if (arrayLength > 2) {
			permissionBit = Integer.valueOf(array[2]);
		}

		if (arrayLength > 3) {
			instanceId = array[3];
		}

		if (StringUtils.isEmpty(instanceId)) {
			instanceId = "*";
		}

	}

	public boolean implies(Permission p) {

		if (!(p instanceof BitPermission)) {
			return false;
		}

		BitPermission other = (BitPermission) p;

		if (!("*".equals(this.resourceIdentify) || this.resourceIdentify
				.equals(other.resourceIdentify))) {
			return false;
		}

		if (!(this.permissionBit == 0 || (this.permissionBit & other.permissionBit) != 0)) {
			return false;
		}

		if (!("*".equals(this.instanceId) || this.instanceId
				.equals(other.instanceId))) {
			return false;
		}

		return true;
	}
	
	@Override
    public String toString() {
        return "BitPermission{" +
                "resourceIdentify='" + resourceIdentify + '\'' +
                ", permissionBit=" + permissionBit +
                ", instanceId='" + instanceId + '\'' +
                '}';
    }
	

}
