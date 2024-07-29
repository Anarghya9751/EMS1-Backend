package com.Ems.Exception;

public class RoleNotFoundException extends RuntimeException{
	public RoleNotFoundException (Long roleId) {
		super("Role with ID"+roleId+"not found");
	}

}
