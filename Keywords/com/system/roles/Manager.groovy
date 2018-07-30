package com.system.roles

import com.system.enums.EnumLanguage
import com.system.enums.EnumRole

public class Manager extends User{

	public Manager(EnumLanguage lang){
		super(EnumRole.MANAGER,lang)
	}
}
