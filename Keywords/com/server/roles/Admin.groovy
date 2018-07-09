package com.server.roles

import com.pages.Kms_Page
import com.server.enums.Enum_Language
import com.server.enums.Enum_Role

public class Admin extends Kms_Page{

	public Admin(Enum_Language lang ){
		super(Enum_Role.ADMINISTRATOR,lang)
	}
}
