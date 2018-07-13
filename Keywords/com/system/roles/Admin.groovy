package com.system.roles

import com.system.enums.Enum_Language
import com.system.enums.Enum_Role

public class Admin extends User{

	public Admin(Enum_Language lang ){
		super(Enum_Role.ADMINISTRATOR,lang)
	}
}
