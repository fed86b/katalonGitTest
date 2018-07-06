package com.server.roles

import com.server.enums.Enum_Language
import com.server.enums.Enum_Role

public class Admin extends User{

	public Admin(Enum_Language lang ){
		super(Enum_Role.ADMINISTRATOR,lang)
	}
}
