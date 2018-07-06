package com.server.roles

import com.server.enums.Enum_Language
import com.server.enums.Enum_Role

public class Manager extends User{

	public Manager(Enum_Language lang){
		super(Enum_Role.MANAGER,lang)
	}
}
