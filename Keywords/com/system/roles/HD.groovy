package com.system.roles

import com.pages.Kms_Page
import com.system.enums.Enum_Language
import com.system.enums.Enum_Role

public class HD extends Kms_Page{

	public HD(Enum_Language lang ){
		super(Enum_Role.HELP_DESK,lang)
	}
}
