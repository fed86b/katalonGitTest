package com.system.roles

import com.pages.Kms_Page
import com.system.enums.Enum_Language
import com.system.enums.Enum_Role

public class Manager extends Kms_Page{

	public Manager(Enum_Language lang){
		super(Enum_Role.MANAGER,lang)
	}
}
