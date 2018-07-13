package com.system.roles

import com.system.enums.Enum_Language
import com.system.enums.Enum_Role

public class SCR extends User{
	public SCR(Enum_Language lang){
		super(Enum_Role.SCR,lang)
	}
}
