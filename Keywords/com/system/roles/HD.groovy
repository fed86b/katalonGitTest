package com.system.roles

import com.system.enums.Enum_Language
import com.system.enums.Enum_Role

public class HD extends User{

	public HD(Enum_Language lang ){
		super(Enum_Role.HELP_DESK,lang)
	}
}
