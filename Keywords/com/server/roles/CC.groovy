package com.server.roles

import com.pages.Kms_Page
import com.server.enums.Enum_Language
import com.server.enums.Enum_Role

public class CC extends Kms_Page {

	public CC(Enum_Language lang ){
		super(Enum_Role.CONTENT_CONTRIBUTOR,lang)
	}
}
