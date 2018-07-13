package com.system.roles

import com.system.enums.Enum_Language
import com.system.enums.Enum_Role

public class Manager extends User{

	public Manager(Enum_Language lang){
		super(Enum_Role.MANAGER,lang)
	}
}
