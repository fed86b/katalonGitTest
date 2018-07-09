package com.server.roles
import com.pages.Kms_Page
import com.server.TemplateHelper
import com.server.enums.Enum_Language
import com.server.enums.Enum_Role

public abstract  class CM extends  Kms_Page{
	protected static TemplateHelper tempHelper

	protected CM(Enum_Language lang ){
		super(Enum_Role.CONTENT_MANAGER,lang)
		tempHelper=new TemplateHelper()
	}

	protected static delete_all_created_items(String yes){
		tempHelper.getTemplate()._open_lastFolder()
		tempHelper.getTemplate().delete_items(yes)
	}
}
