package com.system.roles

import com.system.enums.EnumLanguage
import com.system.enums.EnumRole

public class HD extends User{

	public HD(EnumLanguage lang ){
		super(EnumRole.HELP_DESK,lang)
	}
}
