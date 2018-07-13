package com.system.roles

import com.pages.Kms_Page
import com.system.enums.Enum_Language
import com.system.enums.Enum_Role

public class Admin extends Kms_Page{

	public Admin(Enum_Language lang ){
		super(Enum_Role.ADMINISTRATOR,lang)
	}
}
