package com.system.roles

import com.system.enums.EnumLanguage
import com.system.enums.EnumRole

public class Admin extends User{

	public Admin(EnumLanguage lang ){
		super(EnumRole.ADMINISTRATOR,lang)
	}
}
