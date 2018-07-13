package com.system

import com.system.roles.languages.Admin_En
import com.system.roles.languages.Admin_Ru
import com.system.roles.languages.CM_En

public class RolesHelper {

	private static CM_En cm_En
	private static Admin_En admin_En
	private static Admin_Ru admin_Ru

	public static CM_En getCm_En() {

		return cm_En=new CM_En();
	}
	public static Admin_En getAdmin_En() {
		return admin_En=new Admin_En();
	}
	public static Admin_Ru getAdmin_Ru() {
		return admin_Ru=new Admin_Ru();
	}
}
