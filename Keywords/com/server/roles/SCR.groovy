package com.server.roles

import com.server.enums.Enum_Language
import com.server.enums.Enum_Role

public class SCR extends User{

	public SCR(Enum_Language lang){

		super(Enum_Role.SCR,lang)
	}
}
