package com.system.roles

import com.system.enums.EnumLanguage
import com.system.enums.EnumRole

public class SCR extends User{
	public SCR(EnumLanguage lang){
		super(EnumRole.SCR,lang)
	}
}
