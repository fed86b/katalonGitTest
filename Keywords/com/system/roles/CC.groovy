package com.system.roles

import com.system.enums.EnumLanguage
import com.system.enums.EnumRole

public class CC extends User {

	public CC(EnumLanguage lang ){
		super(EnumRole.CONTENT_CONTRIBUTOR,lang)
	}
}
