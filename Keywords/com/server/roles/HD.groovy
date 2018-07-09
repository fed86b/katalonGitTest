package com.server.roles

import com.pages.Kms_Page
import com.server.enums.Enum_Language
import com.server.enums.Enum_Role

public class HD extends Kms_Page{

	public HD(Enum_Language lang ){
		super(Enum_Role.HELP_DESK,lang)
	}
}
