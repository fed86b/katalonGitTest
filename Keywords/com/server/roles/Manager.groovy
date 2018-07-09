package com.server.roles

import com.pages.Kms_Page
import com.server.enums.Enum_Language
import com.server.enums.Enum_Role

public class Manager extends Kms_Page{

	public Manager(Enum_Language lang){
		super(Enum_Role.MANAGER,lang)
	}
}
